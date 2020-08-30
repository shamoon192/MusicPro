package com.shamoon.musicpro.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shamoon.musicpro.controller.service.ApiInterface
import com.shamoon.musicpro.controller.service.RetrofitService
import com.shamoon.musicpro.data.CHandler
import com.shamoon.musicpro.data.api_model.Album
import com.shamoon.musicpro.data.api_model.SearchResponse
import com.shamoon.musicpro.data.types.SearchListItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AlbumRepository() {
    private val albumList: ArrayList<Album> = ArrayList()
    val albums: MutableLiveData<ArrayList<Album>> by lazy {
        MutableLiveData<ArrayList<Album>>()
    }

    fun getAlbums(album: String) {
        val callback: Callback<SearchResponse?> =
            object : Callback<SearchResponse?> {
                override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                    Log.e("Album", t.message, t)
                }

                override fun onResponse(
                    call: Call<SearchResponse?>,
                    response: Response<SearchResponse?>
                ) {
                    if (response.isSuccessful){
                        val body: SearchResponse? = response.body()
                        albums.setValue(body!!.results!!.albummatches!!.album as ArrayList<Album>?)
                    }
                }

            }

        RetrofitService().getApi()!!.search("album.search", null, CHandler().getAPIKey(), "json", album, null, 20)!!.enqueue(callback)
    }
}