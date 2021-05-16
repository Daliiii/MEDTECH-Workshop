package com.medtech.loginscreenapp.cat.network

import com.medtech.loginscreenapp.cat.CatItem
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {

    @GET("/cats")
    fun getCats(): Call<List<CatItem>>
}