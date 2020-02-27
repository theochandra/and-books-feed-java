package com.android.booksfeedjava.presentation.search;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.booksfeedjava.ApplicationComponent;
import com.android.booksfeedjava.R;
import com.android.booksfeedjava.base.BaseActivity;
import com.android.booksfeedjava.component.EndlessScrollListener;
import com.android.booksfeedjava.presentation.modelview.BooksModelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class SearchListActivity extends BaseActivity
    implements SearchListContract.View {

    private static final int ITEM_INVISIBLE_THRESHOLD = 1;

    @BindView(R.id.rv_book_list)
    RecyclerView mRvBookList;

    @BindView(R.id.et_search_query)
    EditText mEtSearchQuery;

    @BindView(R.id.iv_clear_query)
    ImageView mIvClearQuery;

    @BindView(R.id.layout_search_tutorial)
    ConstraintLayout mLayoutSearchTutorial;

    @BindView(R.id.layout_item_not_found)
    ConstraintLayout mLayoutItemNotFound;

    @BindView(R.id.layout_error)
    ConstraintLayout mLayoutError;

    private SearchListAdapter mAdapter;

    private List<BooksModelView> mBooksModelViewList = new ArrayList<>();

    private SearchListContract.Presenter mPresenter;

    private boolean mSeamlessFetchValid;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideLeftMenu();
        initPresenter();
        initView();
        initListener();
        initRecyclerView();
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

    private void initView() {
        mRvBookList.setVisibility(View.GONE);
        mLayoutSearchTutorial.setVisibility(View.VISIBLE);
    }

    private void initRecyclerView() {
//        int space = getResources().getDimensionPixelSize(R.dimen.margin_s);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mAdapter = new SearchListAdapter(this);
        mRvBookList.setAdapter(mAdapter);
//        mRvBookList.addItemDecoration(new GridLayoutSpacesItemDecoration(space));
        mRvBookList.setLayoutManager(layoutManager);
        mAdapter.setItemListener(booksModelView -> {

        });

        EndlessScrollListener endlessScrollListener = new EndlessScrollListener(
            layoutManager, ITEM_INVISIBLE_THRESHOLD) {
            @Override
            public void onLoadMore() {
                if (mSeamlessFetchValid)
                    loadMoreData();
            }
        };
        endlessScrollListener.setLoadMoreEnable(true);
        mRvBookList.addOnScrollListener(endlessScrollListener);
    }

    private void initListener() {
        mEtSearchQuery.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                retrieveBooksByQuery(v.getText().toString());
                hideKeyboard();
                mEtSearchQuery.clearFocus();
                return true;
            }
            return false;
        });
    }

    private void loadMoreData() {
        retrieveBooksByQuery("res");
    }

    private void retrieveBooksByQuery(String keyword) {
        mPresenter.retrieveBooksByQuery(keyword);
    }

    @Override
    public void populateBooks(List<BooksModelView> booksModelViewList, int totalItems) {
        mSeamlessFetchValid = mAdapter.getItemCount() < totalItems;
        mAdapter.setFooterEnabled(mSeamlessFetchValid);
//        mAdapter.setBooksModelViewList(booksModelViewList);

        for (BooksModelView booksModelView : booksModelViewList)
            mAdapter.addData(booksModelView);
    }

    @Override
    public void hideLoadingBar() {
//        super.hideLoadingBar();
    }

    @Override
    public void showLoadingBar() {
//        super.showLoadingBar();
    }

    @Override
    public void hideDisplayLayout() {
        mRvBookList.setVisibility(View.GONE);
    }

    @Override
    public void showDisplayLayout() {
        mLayoutSearchTutorial.setVisibility(View.GONE);
        mRvBookList.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSearchNotFoundLayout() {
        mLayoutItemNotFound.setVisibility(View.GONE);
    }

    @Override
    public void showSearchNotFoundLayout() {
        mLayoutSearchTutorial.setVisibility(View.GONE);
        mLayoutItemNotFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorLayout() {
        mLayoutError.setVisibility(View.GONE);
    }

    @Override
    public void showErrorLayout() {
        mLayoutSearchTutorial.setVisibility(View.GONE);
        mLayoutError.setVisibility(View.VISIBLE);
    }

    @OnTextChanged(R.id.et_search_query)
    public void onQuerySearchTextChanged(CharSequence text) {
        if (text.length() != 0)
            mIvClearQuery.setVisibility(View.VISIBLE);
        else mIvClearQuery.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.iv_clear_query)
    public void onClearQueryClicked() {
        mEtSearchQuery.setText("");
    }

}
