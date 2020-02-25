package com.android.booksfeedjava.data.network.retrofit;

import com.android.booksfeedjava.data.network.response.BooksResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitRestApi {

    public static final String REUQEST_HEADER_CONTENT_TYPE = "Content-Type: application/json";

    @Headers({REUQEST_HEADER_CONTENT_TYPE})
    @GET
    Observable<BooksResponse> retrieveBooksByQuery(@Url String fullUrl,
                                                   @Query("q") String keyword);

}
