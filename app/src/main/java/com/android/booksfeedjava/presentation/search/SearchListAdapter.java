package com.android.booksfeedjava.presentation.search;

import android.content.Context;
import android.text.TextUtils;
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

public class SearchListAdapter extends RecyclerView.Adapter {

    private static final int LOADING_VIEW = 100;

    private static final int CONTENT_VIEW = 200;

    private static final String EMPTY_STRING_PLACEHOLDER = "-";

    private SearchListItemListener mItemListener;

    private List<BooksModelView> mBooksModelViewList;

    private Context mContext;

    private boolean mFooterEnabled;

    public SearchListAdapter(Context context) {
        mContext = context;
        mBooksModelViewList = new ArrayList<>();
    }

    public void setItemListener(SearchListItemListener itemListener) {
        mItemListener = itemListener;
    }

    public void addData(BooksModelView booksModelView) {
        mBooksModelViewList.add(booksModelView);
        notifyItemInserted(getItemCount() - 1);
    }

    public void clearData() {
        mBooksModelViewList.clear();
    }

    public void setFooterEnabled(boolean footerEnabled) {
        mFooterEnabled = footerEnabled;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LOADING_VIEW) {
            View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_footer_loading, parent, false);
            return new LoadingViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
            return new BookViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BookViewHolder) {
            BookViewHolder bookViewHolder = (BookViewHolder) holder;
            BooksModelView booksModelView = mBooksModelViewList.get(position);
            bookViewHolder.bindData(booksModelView);
            bookViewHolder.itemView.setOnClickListener(v -> mItemListener.onItemClicked(booksModelView));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mBooksModelViewList.size() && mFooterEnabled) {
            return LOADING_VIEW;
        } else {
            return CONTENT_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return mFooterEnabled && !mBooksModelViewList.isEmpty()
            ? mBooksModelViewList.size() + 1 : mBooksModelViewList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_book_thumbnail)
        ImageView mIvBookThumbnail;

        @BindView(R.id.tv_book_category)
        TextView mTvBookCategory;

        @BindView(R.id.tv_book_title)
        TextView mTvBookTitle;

        @BindView(R.id.tv_book_description)
        TextView mTvBookDescription;

        @BindView(R.id.tv_book_authors)
        TextView mTvBookAuthors;

        @BindView(R.id.tv_published_date)
        TextView mTvPublishedDate;

        @BindView(R.id.tv_pages_count)
        TextView mTvPagesCount;

        @BindView(R.id.rb_book_rating)
        RatingBar mRbBookRating;

        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(BooksModelView booksModelView) {
            mTvBookAuthors.setText(booksModelView.getAuthors());
            mTvBookCategory.setText(booksModelView.getCategories());
            mTvBookTitle.setText(booksModelView.getTitle());
            mTvBookDescription.setText(booksModelView.getDescription());

            if (!TextUtils.isEmpty(booksModelView.getPublishedDate()))
                mTvPublishedDate.setText(booksModelView.getPublishedDate());
            else mTvPublishedDate.setText(mContext.getString(R.string.label_not_yet_published));

            mTvPagesCount.setText(
                    mContext.getString(R.string.label_page_count, booksModelView.getPageCount()));
            mRbBookRating.setRating(booksModelView.getRating());

            Picasso.get().load(booksModelView.getThumbnailUrl())
                    .placeholder(R.drawable.image_placeholder)
                    .into(mIvBookThumbnail);
        }

    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
        }

    }

    public interface SearchListItemListener {

        void onItemClicked(BooksModelView booksModelView);

    }

}
