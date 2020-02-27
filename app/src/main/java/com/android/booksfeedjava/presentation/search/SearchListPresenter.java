package com.android.booksfeedjava.presentation.search;

import com.android.booksfeedjava.data.network.response.BooksResponse;
import com.android.booksfeedjava.domain.usecase.RetrieveBooksByQueryUseCase;
import com.android.booksfeedjava.presentation.mapper.BooksMapper;

import io.reactivex.observers.DisposableObserver;

public class SearchListPresenter implements SearchListContract.Presenter {

    private static final String PARAM_PRINT_TYPE = "books";

    private static final int PARAM_MAX_RESULTS = 10;

    private RetrieveBooksByQueryUseCase mUseCase;

    private SearchListContract.View mView;

    private int mStartIndex = 0;

    public SearchListPresenter(
            RetrieveBooksByQueryUseCase useCase,
            SearchListContract.View view) {
        mUseCase = useCase;
        mView = view;
    }

    @Override
    public void retrieveBooksByQuery(String keyword) {
        mView.hideDisplayLayout();
        mView.hideErrorLayout();
        mView.hideSearchNotFoundLayout();
        mView.showLoadingBar();

        mUseCase.setKeyword(keyword);
        mUseCase.setStartIndex(mStartIndex);
        mUseCase.setPrintType(PARAM_PRINT_TYPE);
        mUseCase.setMaxResults(PARAM_MAX_RESULTS);

        mUseCase.execute(new DisposableObserver<BooksResponse>() {
            @Override
            public void onNext(BooksResponse response) {
                mView.hideLoadingBar();
                if (response != null) {
                    if (!response.getItems().isEmpty() && response.getItems() != null) {
                        mView.showDisplayLayout();
                        mView.populateBooks(BooksMapper.transform(response), response.getTotalItems());
                        mStartIndex += PARAM_MAX_RESULTS;
                    } else {
                        mView.showSearchNotFoundLayout();
                    }

//                    if (response.getTotalItems() > 0 ) {
//
//                    }
                } else {
                    mView.showErrorLayout();
                }
            }

            @Override
            public void onError(Throwable e) {
                mView.hideLoadingBar();
                mView.showErrorLayout();
            }

            @Override
            public void onComplete() {
                // default implementation ignored
            }
        });
    }

    @Override
    public void destroy() {
        mUseCase.unsubscribe();
    }

}
