package com.example.ctelassign.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.ctelassign.repositary.Repositary
import com.example.ctelassign.repositary.api.ResponseData
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import rx.Subscriber

class VenuesModel(val repositary: Repositary,val compositeDisposable: CompositeDisposable,
                  var subscriber: Subscriber<ResponseData>) :ViewModel() {

    val liveData = MutableLiveData<ResponseData>()

    fun setData(s:String){
       subscriber = object :Subscriber<ResponseData>(){
           override fun onCompleted() {
               TODO("Not yet implemented")
           }

           override fun onError(e: Throwable?) {
               TODO("Not yet implemented")
           }

           override fun onNext(t: ResponseData?) {
              liveData.value = t
           }
       }
        repositary.getVenueDetails(s,"","")
    }
}