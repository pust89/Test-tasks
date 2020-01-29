package com.pustovit.kotlinversion.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pustovit.kotlinversion.R
import com.pustovit.kotlinversion.databinding.ItemAlbumBinding
import com.pustovit.kotlinversion.repository.model.Album
import com.squareup.picasso.Picasso
import java.util.ArrayList

/**
 * Created by Pustovit Vladimir on 29.01.2020.
 * vovapust1989@gmail.com
 */


class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumVH>() {
    private var albumsList: List<Album>
    /**
     * Setting new data for AlbumsAdapter.
     * @param newAlbumsList new album list.
     */
    fun setNewData(newAlbumsList: List<Album>) {
        val diffResult =
            DiffUtil.calculateDiff(AlbumDiffUtil(albumsList, newAlbumsList), false)
        albumsList = newAlbumsList
        diffResult.dispatchUpdatesTo(this@AlbumsAdapter)
    }

    fun getAlbumByAdapterPosition(adapterPosition: Int): Album {
        return albumsList[adapterPosition]
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumVH {
        val itemAlbumBinding: ItemAlbumBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_album,
            parent,
            false
        )
        return AlbumVH(itemAlbumBinding)
    }

    override fun onBindViewHolder(
        holder: AlbumVH,
        position: Int
    ) {
        holder.itemAlbumBinding.album = albumsList[position]
    }

    override fun getItemCount(): Int {
        return if (albumsList == null) 0 else albumsList!!.size
    }

    inner class AlbumVH(itemAlbumBinding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(itemAlbumBinding.itemAlbum) {
         val itemAlbumBinding: ItemAlbumBinding

        init {
            this.itemAlbumBinding = itemAlbumBinding
        }
    }

    companion object {
        /**
         * Method for displaying album image in AlbumAndSongsActivity and recyclerView in MainActivity.
         * @param imageView   imageView
         * @param url  image url
         */
        @BindingAdapter("app:artworkUrl100")
        @JvmStatic
        fun loadImage(imageView: ImageView, url: String) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imageView)
        }
    }

    init {
        albumsList = ArrayList<Album>(0)
    }
}