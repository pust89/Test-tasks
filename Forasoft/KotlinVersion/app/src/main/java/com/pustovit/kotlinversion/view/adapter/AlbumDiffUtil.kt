package com.pustovit.kotlinversion.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pustovit.kotlinversion.repository.model.Album

/**
 * Created by Pustovit Vladimir on 29.01.2020.
 * vovapust1989@gmail.com
 */

internal class AlbumDiffUtil(
    oldList: List<Album>,
    newList: List<Album>
) :
    DiffUtil.Callback() {
    private val oldList: List<Album>?
    private val newList: List<Album>?
    override fun getOldListSize(): Int {
        return oldList?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newList?.size ?: 0
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList!![oldItemPosition].collectionId == newList!![newItemPosition].collectionId
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList!![oldItemPosition].collectionId == newList!![newItemPosition].collectionId
    }

    init {
        this.oldList = oldList
        this.newList = newList
    }
}