package com.shamoon.musicpro.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shamoon.musicpro.controller.service.ApiInterface
import com.shamoon.musicpro.controller.service.RetrofitService
import com.shamoon.musicpro.data.CHandler
import com.shamoon.musicpro.data.api_model.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistRepository {
    private final val TAG: String = this.javaClass.simpleName
    private lateinit var artistRepository: ArtistRepository

    fun getInstance(): ArtistRepository? {
        if (artistRepository == null) {
            artistRepository = ArtistRepository()
        }
        return artistRepository
    }
/*
    fun getArtists(artist: String): MutableLiveData<SearchResponse> {
        val artistData = MutableLiveData<SearchResponse>()
        apiInterface.search("artist.search", null, CHandler().getAPIKey(), "json", artist, null, 3)
            ?.enqueue(object : Callback<SearchResponse?> {
                override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                    t.message?.let { Log.d(TAG, it) }
                    artistData.value = null
                }

                override fun onResponse(
                    call: Call<SearchResponse?>,
                    response: Response<SearchResponse?>
                ) {
                    if (response.isSuccessful){
                        artistData.value = response.body()
                    }
                }

            }
            )
        return artistData
    }*/
}