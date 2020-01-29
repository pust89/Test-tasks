package com.pustovit.forajava.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pustovit.forajava.R;
import com.pustovit.forajava.databinding.ItemAlbumBinding;
import com.pustovit.forajava.repository.model.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class for recyclerView in MainActivity.
 *
 * Created by Pustovit Vladimir on 25.01.2020.
 * vovapust1989@gmail.com
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumVH> {

    private List<Album> albumsList;


    public AlbumsAdapter() {
        albumsList = new ArrayList<>(0);
    }

    /**
     * Setting new data for AlbumsAdapter.
     * @param newAlbumsList new album list.
     */
    public void setNewData(List<Album> newAlbumsList) {
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new AlbumDiffUtil(albumsList, newAlbumsList), false);
        albumsList = newAlbumsList;
        diffResult.dispatchUpdatesTo(AlbumsAdapter.this);
    }


    public Album getAlbumByAdapterPosition(int adapterPosition){
        return albumsList.get(adapterPosition);
    }

    @NonNull
    @Override
    public AlbumVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAlbumBinding itemAlbumBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_album,
                parent,
                false);
        return new AlbumVH(itemAlbumBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull AlbumVH holder, int position) {
        holder.itemAlbumBinding.setAlbum(albumsList.get(position));
    }

    /**
     * Method for displaying album image in AlbumAndSongsActivity and recyclerView in MainActivity.
     * @param imageView   imageView
     * @param url  image url
     */
    @BindingAdapter({"app:artworkUrl100"})
    public static void loadImage(ImageView imageView, String url) {
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imageView);
    }


    @Override
    public int getItemCount() {
        return albumsList == null ? 0 : albumsList.size();
    }



    class AlbumVH extends RecyclerView.ViewHolder {
        private ItemAlbumBinding itemAlbumBinding;

        AlbumVH(@NonNull ItemAlbumBinding itemAlbumBinding) {
            super(itemAlbumBinding.getRoot());
            this.itemAlbumBinding = itemAlbumBinding;

        }
    }
}
