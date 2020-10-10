package com.example.ctelassign.repositary

import android.util.Log
import com.example.ctelassign.repositary.api.ApiService
import com.example.ctelassign.repositary.api.ResponseData
import com.example.ctelassign.repositary.api.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

class Repositary(val retrofitInstance: RetrofitInstance) {

    lateinit var apiService: ApiService
    val oauth_token = "QMPYUTVROORNKAQNETOBMAEKLPXET0U5LGTZCGAKLZFHLGC2"
    val v = "20191120"
    val compositeDisposable = CompositeDisposable()

    fun getVenueDetails(
        disposableObserver: DisposableObserver<ResponseData>,
        query: String,
        latitude: String,
        longitude: String
    ) {
        apiService = retrofitInstance.getInstance().create(ApiService::class.java)
        val query = "search?query=$query&ll=$latitude,$longitude&oauth_token=$oauth_token&v=$v"
        compositeDisposable.add(
            apiService.loadVenueData(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableObserver))
    }

    companion object {
        var repositary: Repositary? = null
        fun getInstance(): Repositary {
            if (repositary == null) {
                repositary = Repositary(RetrofitInstance.getInstance())
            }
            return repositary!!
        }
    }
}