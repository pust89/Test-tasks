package com.pustovit.kotlinversion.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pustovit.kotlinversion.R
import com.pustovit.kotlinversion.databinding.ContentAlbumBinding
import com.pustovit.kotlinversion.repository.model.Album
import com.pustovit.kotlinversion.repository.model.Song
import com.pustovit.kotlinversion.view.adapter.SongAdapter
import java.io.Serializable

class AlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        activateToolbar(true)
        title = ""

        val contentBinding: ContentAlbumBinding =
            DataBindingUtil.setContentView(this@AlbumActivity, R.layout.content_album)

        val bundle = intent.extras
        if (bundle != null) {
            val mAlbum: Album = bundle.getSerializable(Album::class.java.getName()) as Album
            val mSongList: List<Song> = bundle.getSerializable(Song::class.java.getName())as  List<Song>

            contentBinding.setAlbum(mAlbum)

            val songAdapter = SongAdapter(mSongList)
            val rvSongs: RecyclerView = contentBinding.rvSongs
            rvSongs.layoutManager = LinearLayoutManager(this)
            rvSongs.adapter = songAdapter
        }

    }

    fun activateToolbar(enableHome: Boolean) {
        var actionBar = supportActionBar
        if (actionBar == null) {
            val toolbar =
                findViewById<Toolbar>(R.id.toolbar)
            if (toolbar != null) {
                setSupportActionBar(toolbar)
                actionBar = supportActionBar
            }
        }
        actionBar?.setDisplayHomeAsUpEnabled(enableHome)
    }
}
