package com.android.booksfeedjava.presentation.search;

import com.android.booksfeedjava.data.network.response.BooksResponse;
import com.android.booksfeedjava.domain.usecase.RetrieveBooksByQueryUseCase;

import io.reactivex.observers.DisposableObserver;

public class SearchListPresenter implements SearchListContract.Presenter {

    private RetrieveBooksByQueryUseCase mUseCase;

    private SearchListContract.View mView;

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
        mView.showLoadingBar();
        mUseCase.execute(new DisposableObserver<BooksResponse>() {
            @Override
            public void onNext(BooksResponse response) {
                mView.hideLoadingBar();
                if (response != null) {
                    mView.showDisplayLayout();
//                    mView.populateBooks();
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
