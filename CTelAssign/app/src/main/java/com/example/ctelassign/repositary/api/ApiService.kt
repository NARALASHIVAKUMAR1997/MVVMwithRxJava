package com.example.ctelassign.repositary.api

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.*

interface ApiService {

    @POST("v2/venues/search")
    fun loadVenueData(@Query("query") query: String,
                      @Field("ll")lat:String,Long:String,
                      @Field("oauth_token")token:String,
                      @Field("v")v:String
                    ):Observable<ResponseData>

}