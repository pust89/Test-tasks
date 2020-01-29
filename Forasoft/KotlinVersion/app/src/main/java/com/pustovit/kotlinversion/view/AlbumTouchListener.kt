package com.pustovit.kotlinversion.view

import android.content.Context
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SimpleOnItemTouchListener

/**
 *  AlbumTouchListener class.
 *
 * TouchListener for recyclerView in MainActivity.class;
 *
 * Created by Pustovit Vladimir on 27.01.2020.
 * vovapust1989@gmail.com
 */

class AlbumTouchListener(
    context: Context?,
    recyclerView: RecyclerView,
    listener: OnRecyclerClickListener
) :
    SimpleOnItemTouchListener() {
    private val mGestureDetector: GestureDetectorCompat

    /**
     * Callback listener
     */
    interface OnRecyclerClickListener {
        fun onAlbumClick(position: Int)
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        return mGestureDetector.onTouchEvent(e)
    }

    init {
        mGestureDetector = GestureDetectorCompat(context, object : SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                val view = recyclerView.findChildViewUnder(e.x, e.y)
                if (view != null) {
                    listener.onAlbumClick(recyclerView.getChildAdapterPosition(view))
                }
                return true
            }
        })
    }
}