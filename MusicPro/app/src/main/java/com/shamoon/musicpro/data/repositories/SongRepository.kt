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

class SongRepository {
    private final val TAG: String = this.javaClass.simpleName
    private lateinit var songRepository: SongRepository

    fun getInstance(): SongRepository? {
        if (songRepository == null) {
            songRepository = SongRepository()
        }
        return songRepository
    }

   /* fun getSongs(song: String): MutableLiveData<SearchResponse> {
        val songData = MutableLiveData<SearchResponse>()
        apiInterface.search("track.search", null, CHandler().getAPIKey(), "json", song, null, 3)
            ?.enqueue(object : Callback<SearchResponse?> {
                override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                    t.message?.let { Log.d(TAG, it) }
                    songData.value = null
                }

                override fun onResponse(
                    call: Call<SearchResponse?>,
                    response: Response<SearchResponse?>
                ) {
                    if (response.isSuccessful){
                        songData.value = response.body()
                    }
                }

            }
            )
        return songData
    }*/
}