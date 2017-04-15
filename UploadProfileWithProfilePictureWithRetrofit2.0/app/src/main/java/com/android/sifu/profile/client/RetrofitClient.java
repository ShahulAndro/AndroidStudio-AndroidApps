package com.android.sifu.profile.client;

import com.android.sifu.profile.api.ProfileApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    //Todo: Please add your Server Path
    private static final String ROOT_URL = "http://example.com/api/";

    public RetrofitClient() {

    }

    private static Retrofit getRetroClient() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ProfileApi getApiService() {
        return getRetroClient().create(ProfileApi.class);
    }
}
