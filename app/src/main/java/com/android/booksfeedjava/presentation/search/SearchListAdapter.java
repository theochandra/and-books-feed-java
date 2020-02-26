package com.android.booksfeedjava.presentation.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.booksfeedjava.R;
import com.android.booksfeedjava.presentation.modelview.BooksModelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.BookViewHolder> {

    private SearchListItemListener mItemListener;

    private List<BooksModelView> mBooksModelViewList;

    private Context mContext;

    public SearchListAdapter(Context context) {
        mContext = context;
        mBooksModelViewList = new ArrayList<>();
    }

    public void setItemListener(SearchListItemListener itemListener) {
        mItemListener = itemListener;
    }

    public void setBooksModelViewList(List<BooksModelView> booksModelViewList) {
        mBooksModelViewList = booksModelViewList;
    }

    public void clearData() {
        mBooksModelViewList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        if (position < mBooksModelViewList.size()) {
            BooksModelView booksModelView = mBooksModelViewList.get(holder.getAdapterPosition());
            holder.bindData(booksModelView);
            holder.itemView.setOnClickListener(v -> mItemListener.onItemClicked(booksModelView));
        }
    }

    @Override
    public int getItemCount() {
        return mBooksModelViewList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_book_thumbnail)
        ImageView mIvBookThumbnail;

        @BindView(R.id.tv_book_title)
        TextView mTvBookTitle;

        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(BooksModelView booksModelView) {
            mTvBookTitle.setText(booksModelView.getTitle());
        }

    }

    public interface SearchListItemListener {

        void onItemClicked(BooksModelView booksModelView);

    }

}