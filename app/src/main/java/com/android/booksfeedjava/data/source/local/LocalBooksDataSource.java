package com.android.booksfeedjava.data.source.local;

import com.android.booksfeedjava.data.LocalApi;
import com.android.booksfeedjava.data.network.response.BooksResponse;
import com.android.booksfeedjava.data.source.BooksDataSource;

import io.reactivex.Observable;

public class LocalBooksDataSource implements BooksDataSource {

    private LocalApi mContentResolveDataSource;

    private LocalApi mLocalApi;

    public LocalBooksDataSource(LocalApi localApi, LocalApi contentResolveDataSource) {
        mLocalApi = localApi;
        mContentResolveDataSource = contentResolveDataSource;
    }

    @Override
    public Observable<BooksResponse> retrieveBooksByQuery(String keyword) {
        return null;
    }

}
