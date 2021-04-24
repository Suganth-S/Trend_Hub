package com.suganth.trendhub.network;


import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl(Url.URL_DATA)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public Api api = retrofit.create(Api.class);
}
