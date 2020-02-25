package com.android.booksfeedjava.data.source.factory;

import com.android.booksfeedjava.data.RestApi;
import com.android.booksfeedjava.data.network.retrofit.RetrofitRestApiImpl;
import com.android.booksfeedjava.data.source.BooksDataSource;
import com.android.booksfeedjava.data.source.cloud.CloudBooksDataSource;

public class BooksDataSourceFactory {

    // create local and cloud data source

    public BooksDataSource createCloudDataSource() {
        RestApi restApi = new RetrofitRestApiImpl();
        return new CloudBooksDataSource(restApi);
    }

    // local data source ignored because in this project will not use local db or shared preferences
}
