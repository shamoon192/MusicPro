package com.shamoon.musicpro.controller.service

import com.shamoon.musicpro.data.CHandler
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitService {
    private var api: ApiInterface? = null

    public fun getApi(): ApiInterface? {
        if (api == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(CHandler().getAPIURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            api = retrofit.create(ApiInterface::class.java)
        }
        return api
    }
}