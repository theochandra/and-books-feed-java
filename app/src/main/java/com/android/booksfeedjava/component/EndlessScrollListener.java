package com.android.booksfeedjava.component;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private final LinearLayoutManager mLayoutManager;

    // The minimum amount of items to have below your current scroll position before loading more.
    private final int mVisibleThreshold;

    private boolean mLoadMoreAllowed = true;

    // True if we are still waiting for the last set of data to load.
    private boolean mLoading = true;

    // The total number of items in the dataset after the last load
    private int mPreviousTotal = 0;

    protected EndlessScrollListener(LinearLayoutManager linearLayoutManager, int visibleThreshold) {
        mLayoutManager = linearLayoutManager;
        mVisibleThreshold = visibleThreshold;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = mLayoutManager.getItemCount();
        int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

        if (totalItemCount < mPreviousTotal) {
            mPreviousTotal = 0;
            if (totalItemCount == 0) {
                mLoading = true;
            }
        }

        if (mLoading && totalItemCount > mPreviousTotal) {
            mLoading = false;
            mPreviousTotal = totalItemCount;
        }

        if (mLoadMoreAllowed && !mLoading && (totalItemCount - visibleItemCount)
            <= (firstVisibleItem + mVisibleThreshold)) {

            onLoadMore();

            mLoading = true;
        }
    }

    public abstract void onLoadMore();

    public void setLoadMoreEnable(boolean enable) {
        mLoadMoreAllowed = enable;
    }

    public void setPreviousTotal(int previousTotal) {
        mPreviousTotal = previousTotal;
    }

}
