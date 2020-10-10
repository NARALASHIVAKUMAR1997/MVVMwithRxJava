package com.example.ctelassign.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ctelassign.repositary.Repositary
import com.example.ctelassign.repositary.api.ResponseData
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver

class VenuesModel : ViewModel() {

    val liveData = MutableLiveData<ResponseData>()
    lateinit var disposableObserver: DisposableObserver<ResponseData>
    private var repositary: Repositary = Repositary.getInstance()
    val lat ="17.3850"
    val lon ="78.4867"

    fun getdata(string: String) {
        disposableObserver = object : DisposableObserver<ResponseData>() {
            override fun onNext(t: ResponseData) {
               liveData.value = t
            }

            override fun onError(e: Throwable) {
                Log.v("error", e.toString())
            }

            override fun onComplete() {
                Log.v("onComplete", "Complete")
            }
        }
        repositary.getVenueDetails(disposableObserver,string,lat, lon)
    }
}