package com.example.testlistview

import retrofit2.Call
import retrofit2.http.GET

interface DogeApi {

    @GET("posts")
    fun getDoges() : Call<List<Doge>>
}