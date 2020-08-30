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


class AlbumRepository() {
    private final val TAG: String = this.javaClass.simpleName
    private lateinit var albumRepository: AlbumRepository


    fun getInstance(): AlbumRepository? {
        if (albumRepository == null) {
            albumRepository = AlbumRepository()
        }
        return albumRepository
    }

   /* fun getAlbums(album: String): MutableLiveData<SearchResponse> {
        val albumData = MutableLiveData<SearchResponse>()
        apiInterface.search("album.search", null, CHandler().getAPIKey(), "json", album, null, 3)
            ?.enqueue(object : Callback<SearchResponse?> {
                override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                    t.message?.let { Log.d(TAG, it) }
                    albumData.value = null
                }

                override fun onResponse(
                    call: Call<SearchResponse?>,
                    response: Response<SearchResponse?>
                ) {
                    if (response.isSuccessful){
                        albumData.value = response.body()
                    }
                }

            }
            )
        return albumData
    }*/
}