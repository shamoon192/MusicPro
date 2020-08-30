package com.shamoon.musicpro.controller.service

import com.shamoon.musicpro.data.api_model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET("2.0/")
    fun search(
        @Query("method") method: String?,
        @Query("artist") artist: String?,
        @Query("api_key") api_key: String?,
        @Query("format") format: String?,
        @Query("album") album: String?,
        @Query("track") track: String?,
        @Query("limit") limit: Int?
    ): Call<SearchResponse?>?
}