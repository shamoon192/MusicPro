package com.shamoon.musicpro.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shamoon.musicpro.controller.service.RetrofitService
import com.shamoon.musicpro.data.CHandler
import com.shamoon.musicpro.data.api_model.Artist
import com.shamoon.musicpro.data.api_model.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistRepository {
    private val artistList: ArrayList<Artist> = ArrayList()
    val artists: MutableLiveData<ArrayList<Artist>> by lazy {
        MutableLiveData<ArrayList<Artist>>()
    }

    fun getArtist(artist: String) {
        val callback: Callback<SearchResponse?> =
            object : Callback<SearchResponse?> {
                override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                    Log.e("Artist", t.message, t)
                }

                override fun onResponse(
                    call: Call<SearchResponse?>,
                    response: Response<SearchResponse?>
                ) {
                    if (response.isSuccessful){
                        val body: SearchResponse? = response.body()
                        artists.value = body!!.results!!.artistmatches!!.artist as ArrayList<Artist>?
                    }
                }

            }

        RetrofitService().getApi()!!.search("artist.search", artist, CHandler().getAPIKey(), "json", null, null, 20)!!.enqueue(callback)
    }
}