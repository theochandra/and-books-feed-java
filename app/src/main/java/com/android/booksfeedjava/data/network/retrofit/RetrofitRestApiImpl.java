package com.android.booksfeedjava.data.network.retrofit;

import android.os.Build;

import com.android.booksfeedjava.AndroidApplication;
import com.android.booksfeedjava.BuildConfig;
import com.android.booksfeedjava.data.RestApi;
import com.android.booksfeedjava.data.network.response.BooksResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class RetrofitRestApiImpl implements RestApi {

    public static final int CACHE_SIZE_BYTES = 1024 * 1024 * 2;

    private static final String TLS_V12 = "TLSv1.2";

    private RetrofitRestApi mApiService;

    public RetrofitRestApiImpl() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(RestApi.BOOKS_URL)
                .client(createHttpClient(true))
                .build();
        mApiService = retrofit.create(RetrofitRestApi.class);
    }

    private OkHttpClient createHttpClient(boolean cacheable) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.addInterceptor()
        if (cacheable) {
            setCache(httpClient);
        }
        setTimeout(httpClient);
        setLogger(httpClient);
        enableTls12OnPreLollipop(httpClient);
        return httpClient.build();
    }

    private void setCache(OkHttpClient.Builder okHttpClientBuilder) {
        okHttpClientBuilder.cache(
                new Cache(AndroidApplication.getAppContext().getCacheDir(), CACHE_SIZE_BYTES));
    }

    private void setTimeout(OkHttpClient.Builder okHttpClientBuilder) {
        okHttpClientBuilder.readTimeout(RestApi.READ_TIMEOUT, TimeUnit.SECONDS);
        okHttpClientBuilder.connectTimeout(RestApi.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
    }

    private void setLogger(OkHttpClient.Builder okHttpClientBuilder) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(logging);
        }
    }

    private void enableTls12OnPreLollipop(OkHttpClient.Builder client) {
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 22) {
            try {
                SSLContext sc = SSLContext.getInstance(TLS_V12);
                sc.init(null, null, null);
                client.sslSocketFactory(new Tls12SocketFactory(sc.getSocketFactory()));
                ConnectionSpec connectionSpec = new ConnectionSpec.Builder(
                        ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build();
                List<ConnectionSpec> specs = new ArrayList<>();
                specs.add(connectionSpec);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);
                client.connectionSpecs(specs);
            } catch (Exception exc) {
                Timber.tag("Error setting TLS 1.2");
            }
        }
    }

    @Override
    public Observable<BooksResponse> retrieveBooksByQuery(String fullUrl, String keyword) {
        return mApiService.retrieveBooksByQuery(fullUrl, keyword);
    }

}
