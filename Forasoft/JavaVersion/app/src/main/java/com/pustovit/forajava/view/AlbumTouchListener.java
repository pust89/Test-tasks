package com.pustovit.forajava.view;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 *  AlbumTouchListener class.
 *
 * TouchListener for recyclerView in MainActivity.class;
 *
 * Created by Pustovit Vladimir on 27.01.2020.
 * vovapust1989@gmail.com
 */

public class AlbumTouchListener extends RecyclerView.SimpleOnItemTouchListener {

    private GestureDetectorCompat mGestureDetector;

    /**
     * Callback listener
     */
    interface OnRecyclerClickListener {
        void onAlbumClick(int position);

    }

    public AlbumTouchListener(Context context, final RecyclerView recyclerView, final AlbumTouchListener.OnRecyclerClickListener listener) {

        mGestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (view != null) {
                    listener.onAlbumClick(recyclerView.getChildAdapterPosition(view));
                }
                return true;
            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return mGestureDetector.onTouchEvent(e);
    }
}
