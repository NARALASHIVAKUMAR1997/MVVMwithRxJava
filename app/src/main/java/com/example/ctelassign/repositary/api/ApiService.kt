package com.example.ctelassign.repositary.api

import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {

    @POST()
    fun loadVenueData(@Url url:String):Observable<ResponseData>

}