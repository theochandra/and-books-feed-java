package com.android.booksfeedjava.domain.repository;

import com.android.booksfeedjava.data.network.response.BooksResponse;

import io.reactivex.Observable;

public interface BooksRepository {

    Observable<BooksResponse> retrieveBooksByQuery(String keyword, String printType,
                                                   int startIndex, int maxResults);

}
