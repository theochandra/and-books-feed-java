package com.android.booksfeedjava.presentation.search;

import com.android.booksfeedjava.base.BasePresenter;
import com.android.booksfeedjava.base.BaseView;
import com.android.booksfeedjava.presentation.modelview.BooksModelView;

import java.util.List;

public interface SearchListContract {

    interface View extends BaseView {

        void populateBooks(List<BooksModelView> booksModelViewList);

        void hideDisplayLayout();

        void showDisplayLayout();

        void hideErrorLayout();

        void showErrorLayout();

    }

    interface Presenter extends BasePresenter {

        void retrieveBooksByQuery(String keyword);

    }

}
