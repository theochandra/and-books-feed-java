package com.android.booksfeedjava.presentation.search;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.booksfeedjava.ApplicationComponent;
import com.android.booksfeedjava.R;
import com.android.booksfeedjava.base.BaseActivity;
import com.android.booksfeedjava.component.GridLayoutSpacesItemDecoration;
import com.android.booksfeedjava.presentation.modelview.BooksModelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchListActivity extends BaseActivity
    implements SearchListContract.View {

    @BindView(R.id.rv_book_list)
    RecyclerView mRvBookList;

    @BindView(R.id.et_search_query)
    EditText mEtSearchQuery;

    @BindView(R.id.iv_clear_query)
    ImageView mIvClearQuery;

    private SearchListAdapter mAdapter;

    private List<BooksModelView> mBooksModelViewList = new ArrayList<>();

    private SearchListContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideLeftMenu();
        initPresenter();
        initRecyclerView();
        retrieveBooksByQuery("Flower");
    }

    @Override
    public int getContentView() {
        return R.layout.activity_search_list;
    }

    @Override
    public String getScreenTitle() {
        return getString(R.string.label_search_list_title);
    }

    private void initPresenter() {
        mPresenter = new SearchListPresenter(
            ApplicationComponent.provideRetrieveBooksByQuery(),
            this
        );
    }

    private void initRecyclerView() {
        int space = getResources().getDimensionPixelSize(R.dimen.margin_s);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mAdapter = new SearchListAdapter(this);
        mRvBookList.setAdapter(mAdapter);
        mRvBookList.addItemDecoration(new GridLayoutSpacesItemDecoration(space));
        mRvBookList.setLayoutManager(layoutManager);
        mAdapter.setItemListener(booksModelView -> {

        });
    }

    private void retrieveBooksByQuery(String keyword) {
        mPresenter.retrieveBooksByQuery(keyword);
    }

    @Override
    public void populateBooks(List<BooksModelView> booksModelViewList) {
        mAdapter.setBooksModelViewList(booksModelViewList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideDisplayLayout() {

    }

    @Override
    public void showDisplayLayout() {

    }

    @Override
    public void hideErrorLayout() {

    }

    @Override
    public void showErrorLayout() {

    }
}
