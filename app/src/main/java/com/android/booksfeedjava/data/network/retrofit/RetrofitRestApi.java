package com.android.booksfeedjava.data.network.retrofit;

import com.android.booksfeedjava.data.network.response.BooksResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitRestApi {

    String REQUEST_HEADER_CONTENT_TYPE = "Content-Type: application/json";

    @Headers({REQUEST_HEADER_CONTENT_TYPE})
    @GET
    Observable<BooksResponse> retrieveBooksByQuery(@Url String fullUrl,
                                                   @Query("q") String keyword,
                                                   @Query("printType") String printType,
                                                   @Query("startIndex") int startIndex,
                                                   @Query("maxResults") int maxResults);

}
