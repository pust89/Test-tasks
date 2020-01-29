package com.pustovit.forajava.view.adapter;

import androidx.recyclerview.widget.DiffUtil;
import com.pustovit.forajava.repository.model.Album;
import java.util.List;

/**
 * Created by Pustovit Vladimir on 25.01.2020.
 * vovapust1989@gmail.com
 */

 class AlbumDiffUtil extends DiffUtil.Callback{
    private List<Album> oldList;
    private List<Album> newList;

    public AlbumDiffUtil(List<Album> oldList, List<Album> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getCollectionId().equals(newList.get(newItemPosition).getCollectionId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getCollectionId().equals(newList.get(newItemPosition).getCollectionId());
    }
}
