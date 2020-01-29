package com.pustovit.forajava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import android.view.inputmethod.EditorInfo;

import androidx.appcompat.widget.SearchView;

import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;
import com.pustovit.forajava.R;
import com.pustovit.forajava.repository.model.Album;
import com.pustovit.forajava.repository.model.Song;
import com.pustovit.forajava.view.adapter.AlbumsAdapter;
import com.pustovit.forajava.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Activity  where searching and displaying albums.
 * <p>
 * Created by Pustovit Vladimir on 28.01.2020.
 * vovapust1989@gmail.com
 */
public class MainActivity extends AppCompatActivity implements AlbumTouchListener.OnRecyclerClickListener {
    private static final String TAG = "myTag";
    public static final String STATUS_OK = "ok";
    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private AlbumsAdapter albumsAdapter;


    private String status;
    private SearchView searchView;
    private SharedPreferences sharedPreferences;
    private static final String APP_PREFERENCES = "APP_PREFERENCES";
    private static final String PREFERENCES_QUERY = "query";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        prepareRecyclerView();

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        setObserverForStatus();
        findAlbumsByName(sharedPreferences.getString(PREFERENCES_QUERY, "music"));

    }

    /**
     * RecyclerView customization method.
     */
    private void prepareRecyclerView() {
        recyclerView = findViewById(R.id.rvAlbums);
        albumsAdapter = new AlbumsAdapter();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(MainActivity.this, 2);

        } else {
            layoutManager = new GridLayoutManager(MainActivity.this, 3);
        }
        DividerItemDecoration dividerHorizontal = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerHorizontal.setDrawable(getResources().getDrawable(R.drawable.devider_horizontal));
        recyclerView.addItemDecoration(dividerHorizontal);

        DividerItemDecoration dividerVertical = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        dividerVertical.setDrawable(getResources().getDrawable(R.drawable.devider_vertical));
        recyclerView.addItemDecoration(dividerVertical);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(albumsAdapter);

        AlbumTouchListener albumTouchListener = new AlbumTouchListener(this, recyclerView, this);
        recyclerView.addOnItemTouchListener(albumTouchListener);
    }

    /**
     * Implementation of callback AlbumTouchListener.OnRecyclerClickListener. @see {@link AlbumTouchListener.OnRecyclerClickListener}
     *
     * @param position position album in adapter.
     */
    @Override
    public void onAlbumClick(int position) {
        Album album = albumsAdapter.getAlbumByAdapterPosition(position);
        int albumId = album.getCollectionId();

        mainActivityViewModel.findSongsByAlbumId(albumId).observe(this, new Observer<ArrayList<Song>>() {
            @Override
            public void onChanged(ArrayList<Song> songs) {
                Intent intent = new Intent(MainActivity.this, AlbumActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(Album.class.getName(), album);
                bundle.putParcelableArrayList(Song.class.getName(), songs);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }



    /**
     *The method passes the request to the MainActivityViewModel.
     * @param albumName - search query.
     */
    private void findAlbumsByName(String albumName) {
        Log.d(TAG, "MainActivity:findAlbumsByName: albumName" + albumName);
        mainActivityViewModel.findAlbumsByName(albumName).observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    layoutManager.setSpanCount(2);
                } else {
                    layoutManager.setSpanCount(3);
                }
                albumsAdapter.setNewData(albums);
            }
        });

    }

    /**
     *This method subscribe Observer to LiveData with a status information.
     * If something goes wrong, the user will see a snack bar with information.
     */
    private void setObserverForStatus() {
        mainActivityViewModel.getStatus().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!STATUS_OK.equals(s) && s != null) {
                    status = s;
                    Snackbar snackBar = Snackbar.make(findViewById(android.R.id.content), status, Snackbar.LENGTH_INDEFINITE);

                    snackBar.setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackBar.dismiss();
                        }
                    });
                    View snackBarView = snackBar.getView();
                    TextView textView = (TextView) snackBarView.findViewById(R.id.snackbar_text);
                    textView.setMaxLines(3);
                    snackBar.show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        searchView = (SearchView) searchItem.getActionView();
        searchViewSetup(searchView);
        return true;
    }

    /**
     * This method sets up the SearchView.
     */
    private void searchViewSetup(SearchView searchView){
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setFocusable(true);
        searchView.setQuery(sharedPreferences.getString(PREFERENCES_QUERY, ""), false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();

                mainActivityViewModel.addDisposable(Observable.just(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Function<String, String>() {
                            @Override
                            public String apply(String query) throws Exception {
                                sharedPreferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
                                sharedPreferences.edit().putString(PREFERENCES_QUERY, query).apply();
                                return query;
                            }
                        })
                        .subscribeWith(new DisposableObserver<String>() {
                            @Override
                            public void onNext(String s) {
                                findAlbumsByName(s);
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onComplete() {
                            }
                        }));

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }


}
