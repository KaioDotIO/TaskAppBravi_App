package com.kaio.taskappbravi.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("activity")
    fun getPosts() : Call<Tasks>

    @GET("activity")
    fun getPostsByType(
        @Query("type") type:String
    ) : Call<Tasks>
}