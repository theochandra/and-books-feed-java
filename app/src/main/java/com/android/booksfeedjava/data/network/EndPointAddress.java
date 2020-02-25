package com.android.booksfeedjava.data.network;

import com.android.booksfeedjava.BuildConfig;

public class EndPointAddress {

    public static final String VOLUMES = "volumes";

    private EndPointAddress() { }

    public static String getBooksEndPoint(String endpointPath) {
        return BuildConfig.BOOKS_BASE_URL + BuildConfig.API_VERSION + endpointPath;
    }

}
