package com.android.booksfeedjava.presentation.search;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.booksfeedjava.R;
import com.android.booksfeedjava.presentation.modelview.BooksModelView;
import com.squareup.picasso.Picasso;

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

        @BindView(R.id.tv_book_authors)
        TextView mTvBookAuthors;

        @BindView(R.id.rb_book_rating)
        RatingBar mRbBookRating;

        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(BooksModelView booksModelView) {
            StringBuilder authors = new StringBuilder();
            for (String author : booksModelView.getAuthors())
                authors.append(author).append(", ");
            if (authors.length() >= 2)
                mTvBookAuthors.setText(authors.substring(0, authors.length() - 2));
            else mTvBookAuthors.setText(authors.toString());
            mTvBookTitle.setText(booksModelView.getTitle());

            Log.d("TEST", "TEST RATING ::: " + booksModelView.getRating());
            mRbBookRating.setRating(booksModelView.getRating());
            Picasso.get().load(booksModelView.getThumbnailUrl()).into(mIvBookThumbnail);
        }

    }

    public interface SearchListItemListener {

        void onItemClicked(BooksModelView booksModelView);

    }

}
