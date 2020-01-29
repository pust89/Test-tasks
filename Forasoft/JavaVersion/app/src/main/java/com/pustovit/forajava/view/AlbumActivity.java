package com.pustovit.forajava.view;

import android.os.Bundle;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pustovit.forajava.R;
import com.pustovit.forajava.databinding.ContentFullAlbumBinding;
import com.pustovit.forajava.repository.model.Album;
import com.pustovit.forajava.repository.model.Song;
import com.pustovit.forajava.view.adapter.SongAdapter;

import java.util.List;
/**
 * Album detail Activity.
 * <p>
 * Created by Pustovit Vladimir on 28.01.2020.
 * vovapust1989@gmail.com
 */
public class AlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_album);
        activateToolbar(true);
        setTitle("");

        ContentFullAlbumBinding contentBinding = DataBindingUtil.setContentView(AlbumActivity.this, R.layout.content_full_album);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Album album = bundle.getParcelable(Album.class.getName());
            List<Song> songList = bundle.getParcelableArrayList(Song.class.getName());
            contentBinding.setAlbum(album);
            SongAdapter songAdapter = new SongAdapter(songList);
            RecyclerView rvSongs = contentBinding.rvSongs;
            rvSongs.setLayoutManager(new LinearLayoutManager(this));
            rvSongs.setAdapter(songAdapter);
        }

    }

    void activateToolbar(boolean enableHome) {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            Toolbar toolbar = findViewById(R.id.toolbar);

            if (toolbar != null) {
                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();
            }
        }

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(enableHome);
        }
    }

}
