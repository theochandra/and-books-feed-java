package com.android.booksfeedjava.presentation.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.booksfeedjava.R;
import com.android.booksfeedjava.base.BaseActivity;
import com.android.booksfeedjava.presentation.modelview.BooksModelView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

public class BookDetailActivity extends BaseActivity {

    private static final String BUNDLE_BOOK_DATA = "BUNDLE_BOOK_DATA";

    @BindView(R.id.tv_book_title)
    TextView mTvBookTitle;

    @BindView(R.id.tv_book_category)
    TextView mTvBookCategory;

    @BindView(R.id.tv_book_authors)
    TextView mTvBookAuthors;

    @BindView(R.id.tv_published_date)
    TextView mTvPublishedDate;

    @BindView(R.id.tv_pages_count)
    TextView mTvPagesCount;

    @BindView(R.id.tv_book_description)
    TextView mTvBookDescription;

    @BindView(R.id.rb_book_rating)
    RatingBar mRbBookRating;

    @BindView(R.id.iv_book_thumbnail)
    ImageView mIvBookThumbnail;

    private Bundle mBundle;

    private BooksModelView mBook = new BooksModelView();

    public static Intent newIntent(Context packageContext, BooksModelView book) {
        Intent intent = new Intent(packageContext, BookDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_BOOK_DATA, book);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_book_detail;
    }

    @Override
    public String getScreenTitle() {
        return getString(R.string.label_book_detail_title);
    }

    private void initData() {
        mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mBook = mBundle.getParcelable(BUNDLE_BOOK_DATA);
        }
    }

    private void initView() {
        mTvBookTitle.setText(mBook.getTitle());
        mTvBookCategory.setText(mBook.getCategories());
        mTvBookAuthors.setText(mBook.getAuthors());
        mTvPagesCount.setText(getString(R.string.label_page_count2, mBook.getPageCount()));
        mTvBookDescription.setText(mBook.getDescription());
        mRbBookRating.setRating(mBook.getRating());

        if (!TextUtils.isEmpty(mBook.getPublishedDate()))
            mTvPublishedDate.setText(mBook.getPublishedDate());
        else mTvPublishedDate.setText(getString(R.string.label_not_yet_published));

        Picasso.get().load(mBook.getThumbnailUrl())
                .placeholder(R.drawable.image_placeholder)
                .into(mIvBookThumbnail);
    }

    @OnClick(R.id.iv_toolbar_back)
    public void onLeftMenuClicked() {
        onBackPressed();
    }

}
