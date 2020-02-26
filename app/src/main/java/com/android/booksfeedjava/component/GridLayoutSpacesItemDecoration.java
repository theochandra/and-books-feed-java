package com.android.booksfeedjava.component;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridLayoutSpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public GridLayoutSpacesItemDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = mSpace;
        outRect.right = mSpace;
    }
}
