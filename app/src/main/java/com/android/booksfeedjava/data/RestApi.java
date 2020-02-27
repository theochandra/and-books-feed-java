package com.android.booksfeedjava.data;

import com.android.booksfeedjava.BuildConfig;
import com.android.booksfeedjava.data.network.response.BooksResponse;

import io.reactivex.Observable;

public interface RestApi {

    String BOOKS_URL = BuildConfig.BOOKS_BASE_URL + BuildConfig.API_VERSION;

    int CONNECTION_TIMEOUT = 60;

    int READ_TIMEOUT = 60;

    Observable<BooksResponse> retrieveBooksByQuery(String fullUrl, String keyword,
                                                   String printType, int startIndex, int maxResults);

}
