package com.android.booksfeedjava.data.source.cloud;

import com.android.booksfeedjava.data.RestApi;
import com.android.booksfeedjava.data.network.EndPointAddress;
import com.android.booksfeedjava.data.network.response.BooksResponse;
import com.android.booksfeedjava.data.source.BooksDataSource;

import io.reactivex.Observable;

public class CloudBooksDataSource implements BooksDataSource {

    private final RestApi mRestApi;

    public CloudBooksDataSource(RestApi restApi) {
        mRestApi = restApi;
    }

    @Override
    public Observable<BooksResponse> retrieveBooksByQuery(String keyword) {
        String fullUrl = EndPointAddress.getBooksEndPoint(EndPointAddress.VOLUMES);
        return mRestApi.retrieveBooksByQuery(fullUrl, keyword);
    }

}
