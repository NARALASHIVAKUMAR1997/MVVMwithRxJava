package com.example.ctelassign.repositary

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ctelassign.repositary.api.ApiService
import com.example.ctelassign.repositary.api.ResponseData
import com.example.ctelassign.repositary.api.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.subjects.PublishSubject
import rx.Subscriber
import rx.functions.Func1

class Repositary(val retrofitInstance: RetrofitInstance,
    val query: String, val latitude: String, val longitude: String) {

    lateinit var apiService: ApiService
    lateinit var appSchedulars: AppSchedulars
    val oauth_token = "QMPYUTVROORNKAQNETOBMAEKLPXET0U5LGTZCGAKLZFHLGC2"
    val v = "20191120"


    fun getVenueDetails(query: String, latitude: String, longitude: String) {
        apiService = retrofitInstance.getInstance().create(ApiService::class.java)
        apiService.loadVenueData(query, latitude, longitude, oauth_token, v)
            .subscribeOn(appSchedulars.io())
            .observeOn(appSchedulars.mainThread())
            .subscribe()
    }

}