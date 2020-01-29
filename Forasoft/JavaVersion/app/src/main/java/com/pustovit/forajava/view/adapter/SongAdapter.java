package com.pustovit.forajava.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pustovit.forajava.R;
import com.pustovit.forajava.databinding.ItemAlbumBinding;
import com.pustovit.forajava.databinding.ItemSongBinding;
import com.pustovit.forajava.repository.model.Song;

import java.util.List;
import java.util.Locale;

/**
 * Adapter class for recyclerView in AlbumAndSongsActivity.
 *
 * Created by Pustovit Vladimir on 28.01.2020.
 * vovapust1989@gmail.com
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongVH> {
    private List<Song> songs;

    public SongAdapter(List<Song> songs) {
        this.songs = songs;
    }

    @NonNull
    @Override
    public SongVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSongBinding itemSongBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_song, parent, false);

        return new SongVH(itemSongBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongVH holder, int position) {
        holder.itemSongBinding.setSong(songs.get(position));
    }

    @Override
    public int getItemCount() {
        return songs == null ? 0 : songs.size();
    }

    /**
     * Method for displaying track price in AlbumAndSongsActivity.
     * @param textView   TextView
     * @param price  track price.
     */
    @BindingAdapter({"app:songPrice"})
    public static void setSongPrice(TextView textView, Double price) {
        if (price < 0) {
            textView.setText(textView.getContext().getString(R.string.album_only));
        } else {
            textView.setText(String.format(Locale.getDefault(), "$ %.2f", price));
        }
    }

    /**
     * Method for displaying track duration in AlbumAndSongsActivity.
     * @param textView   TextView
     * @param timeMillis  track duration.
     */
    @BindingAdapter({"app:trackTime"})
    public static void setSongTime(TextView textView, Integer timeMillis) {
        timeMillis = timeMillis/1000;
        int min = timeMillis / 60;
        int sec = timeMillis % 60;
        textView.setText(String.format(Locale.getDefault(), "%d:%d", min, sec));

    }

    class SongVH extends RecyclerView.ViewHolder {
        private ItemSongBinding itemSongBinding;

        public SongVH(@NonNull ItemSongBinding itemSongBinding) {
            super(itemSongBinding.getRoot());
            this.itemSongBinding = itemSongBinding;
        }
    }
}
