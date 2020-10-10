package com.example.ctelassign.repositary.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    lateinit var retrofit: Retrofit
    var basePoint = "https://api.foursquare.com/v2/venues/"

    @Synchronized
    fun getInstance(): Retrofit {
        var retrofit: Retrofit? = null
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(basePoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        return retrofit!!
    }

    companion object {
        var retrofitInstance: RetrofitInstance? = null
        fun getInstance(): RetrofitInstance {
            if (retrofitInstance == null) {
                retrofitInstance = RetrofitInstance()
            }
            return retrofitInstance!!
        }
    }

}