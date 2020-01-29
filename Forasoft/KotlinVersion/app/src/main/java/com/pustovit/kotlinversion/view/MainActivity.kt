package com.pustovit.kotlinversion.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.pustovit.kotlinversion.R
import com.pustovit.kotlinversion.repository.model.Album
import com.pustovit.kotlinversion.repository.model.Song
import com.pustovit.kotlinversion.view.adapter.AlbumsAdapter
import com.pustovit.kotlinversion.viewmodel.MainActivityViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

class MainActivity : AppCompatActivity(), AlbumTouchListener.OnRecyclerClickListener {

    private val TAG = "myTag"
    val STATUS_OK = "ok"
    private var mainActivityViewModel: MainActivityViewModel?= null

    private var layoutManager: GridLayoutManager? = null
    private var albumsAdapter: AlbumsAdapter? = null


    private var status: String = ""
    private var searchView: SearchView? = null
    private var sharedPreferences: SharedPreferences? = null
    private val APP_PREFERENCES = "APP_PREFERENCES"
    private val PREFERENCES_QUERY = "query"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar =
            findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        prepareRecyclerView()
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        setObserverForStatus()
        sharedPreferences!!.getString(PREFERENCES_QUERY, "music")?.let { findAlbumsByName(it) }
    }

    /**
     * RecyclerView customization method.
     */
    private fun prepareRecyclerView() {
      var  recyclerView : RecyclerView = findViewById(R.id.rvAlbums)
        albumsAdapter = AlbumsAdapter()
        layoutManager =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(this@MainActivity, 2)
            } else {
                GridLayoutManager(this@MainActivity, 3)
            }
        val dividerHorizontal =
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        dividerHorizontal.setDrawable(resources.getDrawable(R.drawable.devider_horizontal))
        recyclerView.addItemDecoration(dividerHorizontal)
        val dividerVertical =
            DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        dividerVertical.setDrawable(resources.getDrawable(R.drawable.devider_vertical))
        recyclerView.addItemDecoration(dividerVertical)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(albumsAdapter)
        val albumTouchListener = AlbumTouchListener(this, recyclerView, this)
        recyclerView.addOnItemTouchListener(albumTouchListener)
    }

    /**
     * Implementation of callback AlbumTouchListener.OnRecyclerClickListener. @see [AlbumTouchListener.OnRecyclerClickListener]
     *
     * @param position position album in adapter.
     */
    override fun onAlbumClick(position: Int) {
        val album: Album = albumsAdapter!!.getAlbumByAdapterPosition(position)
        val albumId: Int = album.collectionId
        mainActivityViewModel!!.findSongsByAlbumId(albumId)
            .observe(this, object : Observer<ArrayList<Song>> {
                override fun onChanged(songs: ArrayList<Song>) {
                    val intent = Intent(this@MainActivity, AlbumActivity::class.java)
                    val bundle = Bundle()
                   bundle.putSerializable(Album::class.java.getName(),album);
                    bundle.putSerializable(Song::class.java.getName(), songs)
                    intent.putExtras(bundle)
                    startActivity(intent)
                }
            })
    }


    /**
     * The method passes the request to the MainActivityViewModel.
     * @param albumName - search query.
     */
    private fun findAlbumsByName(albumName: String) {
        Log.d(TAG, "MainActivity:findAlbumsByName: albumName$albumName")
        mainActivityViewModel!!.findAlbumsByName(albumName)
            .observe(this, object : Observer<List<Album>> {
                override fun onChanged(albums: List<Album>) {
                    if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                        layoutManager!!.spanCount = 2
                    } else {
                        layoutManager!!.spanCount = 3
                    }
                    albumsAdapter!!.setNewData(albums)
                }
            })
    }

    /**
     * This method subscribe Observer to LiveData with a status information.
     * If something goes wrong, the user will see a snack bar with information.
     */
    private fun setObserverForStatus() {
        mainActivityViewModel?.getStatus()!!.observe(this, Observer<String?> { s ->
                if (STATUS_OK != s && s != null) {
                    status = s
                    val snackBar = Snackbar.make(
                        findViewById(android.R.id.content),
                        status!!,
                        Snackbar.LENGTH_INDEFINITE
                    )
                    snackBar.setAction(
                        "OK"
                    ) { snackBar.dismiss() }
                    val snackBarView = snackBar.view
                    val textView =
                        snackBarView.findViewById<View>(R.id.snackbar_text) as TextView
                    textView.maxLines = 3
                    snackBar.show()
                }
            })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchViewSetup(searchView)
        return true
    }

    /**
     * This method sets up the SearchView.
     */
    private fun searchViewSetup(searchView: SearchView?) {
        searchView!!.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.isFocusable = true
        searchView.setQuery(sharedPreferences!!.getString(PREFERENCES_QUERY, ""), false)
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                mainActivityViewModel!!.addDisposable(
                    Observable.just(
                        query
                    )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map<String>(object : Function<String, String> {
                            @Throws(Exception::class)
                            override fun apply(query: String): String {
                                sharedPreferences = getSharedPreferences(
                                    APP_PREFERENCES,
                                    Context.MODE_PRIVATE
                                )
                                sharedPreferences!!.edit().putString(PREFERENCES_QUERY, query).apply()
                                return query
                            }
                        })
                        .subscribeWith(object : DisposableObserver<String>() {
                            override fun onNext(s: String) {
                                findAlbumsByName(s)
                            }

                            override fun onError(e: Throwable) {}
                            override fun onComplete() {}
                        })
                )
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }


}
