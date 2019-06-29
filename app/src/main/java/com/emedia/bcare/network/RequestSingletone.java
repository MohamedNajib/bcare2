package com.emedia.bcare.network;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RequestSingletone {

    static RequestSingletone instance;
    public static final String URL_BASE = urls.URL_BASE;
    private static Retrofit retrofit = null;
    private RequestSingletone() {}
    public static synchronized RequestSingletone getInstance() {
        if (instance == null) {
            instance = new RequestSingletone();
        }
        return instance;
    }

    public Retrofit getClient() {
        List<Protocol> protocols = new ArrayList<>();
        protocols.add(Protocol.SPDY_3);
        protocols.add(Protocol.HTTP_2);
        protocols.add(Protocol.HTTP_1_1);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.protocols(protocols);

        HttpLoggingInterceptor loggingHeader = new HttpLoggingInterceptor();
        loggingHeader.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client.networkInterceptors().add(loggingHeader);
        client.addNetworkInterceptor(interceptor);
        //client.addNetworkInterceptor(new StethoInterceptor());
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
