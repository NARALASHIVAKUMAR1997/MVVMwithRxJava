package com.example.ctelassign.repositary.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    lateinit var retrofit: Retrofit
    var basePoint = "https://api.foursquare.com/"

    @Synchronized
    fun getInstance(): Retrofit {
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(basePoint)
        return retrofit
    }

}