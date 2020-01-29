package com.pustovit.kotlinversion.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pustovit.kotlinversion.R
import com.pustovit.kotlinversion.databinding.ItemSongBinding
import com.pustovit.kotlinversion.repository.model.Song
import java.util.*

/**
 * Created by Pustovit Vladimir on 29.01.2020.
 * vovapust1989@gmail.com
 */

class SongAdapter(songs: List<Song>) :
    RecyclerView.Adapter<SongAdapter.SongVH>() {
    private val songs: List<Song>?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongVH {
        val itemSongBinding: ItemSongBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_song, parent, false
        )
        return SongVH(itemSongBinding)
    }

    override fun onBindViewHolder(holder: SongVH, position: Int) {
        holder.itemSongBinding.setSong(songs!![position])
    }

    override fun getItemCount(): Int {
        return songs?.size ?: 0
    }

    inner class SongVH(itemSongBinding: ItemSongBinding) :
        RecyclerView.ViewHolder(itemSongBinding.itemSong) {
         val itemSongBinding: ItemSongBinding

        init {
            this.itemSongBinding = itemSongBinding
        }
    }

    companion object {
        /**
         * Method for displaying track price in AlbumAndSongsActivity.
         * @param textView   TextView
         * @param price  track price.
         */
        @BindingAdapter("app:songPrice")
        @JvmStatic
        fun setSongPrice(textView: TextView, price: Double) {
            if (price < 0) {
                textView.text = textView.context.getString(R.string.album_only)
            } else {
                textView.text = String.format(
                    Locale.getDefault(),
                    "$ %.2f",
                    price
                )
            }
        }

        /**
         * Method for displaying track duration in AlbumAndSongsActivity.
         * @param textView   TextView
         * @param timeMillis  track duration.
         */
        @BindingAdapter("app:trackTime")
        @JvmStatic
        fun setSongTime(textView: TextView, timeMillis: Int) {
            var timeMillis = timeMillis
            timeMillis = timeMillis / 1000
            val min = timeMillis / 60
            val sec = timeMillis % 60
            textView.text = String.format(Locale.getDefault(), "%d:%d", min, sec)
        }
    }

    init {
        this.songs = songs
    }
}