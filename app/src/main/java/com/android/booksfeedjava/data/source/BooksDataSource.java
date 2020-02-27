package com.android.booksfeedjava.data.source;

import com.android.booksfeedjava.data.network.response.BooksResponse;

import io.reactivex.Observable;

public interface BooksDataSource {

    Observable<BooksResponse> retrieveBooksByQuery(String keyword, String printType,
                                                   int startIndex, int maxResults);

}
