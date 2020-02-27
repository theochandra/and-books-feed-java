package com.android.booksfeedjava.domain.repository;

import com.android.booksfeedjava.data.network.response.BooksResponse;
import com.android.booksfeedjava.data.source.BooksDataSource;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BooksDataRepository implements BooksRepository {

    BooksDataSource mBooksCloudDataSource;

    public BooksDataRepository(BooksDataSource booksCloudDataSource) {
        mBooksCloudDataSource = booksCloudDataSource;
    }

    @Override
    public Observable<BooksResponse> retrieveBooksByQuery(String keyword, String printType,
                                                          int startIndex, int maxResults) {
        return mBooksCloudDataSource.retrieveBooksByQuery(keyword, printType, startIndex, maxResults)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
