package com.shamoon.musicpro.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shamoon.musicpro.controller.service.ApiInterface
import com.shamoon.musicpro.controller.service.RetrofitService
import com.shamoon.musicpro.data.CHandler
import com.shamoon.musicpro.data.api_model.SearchResponse
import com.shamoon.musicpro.data.api_model.Track
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SongRepository {
    private val songList: ArrayList<Track> = ArrayList()
    val songs: MutableLiveData<ArrayList<Track>> by lazy {
        MutableLiveData<ArrayList<Track>>()
    }

    fun getSongs(song: String) {
        val callback: Callback<SearchResponse?> =
            object : Callback<SearchResponse?> {
                override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                    Log.e("Track", t.message, t)
                }

                override fun onResponse(
                    call: Call<SearchResponse?>,
                    response: Response<SearchResponse?>
                ) {
                    if (response.isSuccessful){
                        val body: SearchResponse? = response.body()
                        songs.value = body!!.results!!.trackmatches!!.track as ArrayList<Track>?
                    }
                }

            }

        RetrofitService().getApi()!!.search("track.search", null, CHandler().getAPIKey(), "json", null, song, 20)!!.enqueue(callback)
    }
}