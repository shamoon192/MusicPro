package com.shamoon.musicpro.data.repositories

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.shamoon.musicpro.controller.Utils
import com.shamoon.musicpro.controller.service.RetrofitService
import com.shamoon.musicpro.data.CHandler
import com.shamoon.musicpro.data.api_model.Album
import com.shamoon.musicpro.data.api_model.Artist
import com.shamoon.musicpro.data.api_model.SearchResponse
import com.shamoon.musicpro.data.api_model.Track
import com.shamoon.musicpro.data.types.SearchItemType
import com.shamoon.musicpro.data.types.SearchListItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SearchRepository : BaseObservable() {
    private val TAG: String = this.javaClass.simpleName
    private val searchList: ArrayList<SearchListItem> = ArrayList()
    val searchMutableList: MutableLiveData<ArrayList<SearchListItem>> by lazy {
        MutableLiveData<ArrayList<SearchListItem>>()
    }

    private fun addDefaultOptions(query: String){
        searchList.clear()
        searchList.add(
            0, SearchListItem(
                null,
                "Search By Album",
                null,
                null,
                null,
                SearchItemType.SEARCH_BY_ALBUM,
                query
            )
        )
        searchList.add(
            1, SearchListItem(
                null,
                "Search By Artist",
                null,
                null,
                null,
                SearchItemType.SEARCH_BY_ARTIST,
                query
            )
        )
        searchList.add(
            2, SearchListItem(
                null,
                "Search By Song",
                null,
                null,
                null,
                SearchItemType.SEARCH_BY_SONG,
                query
            )
        )
        searchMutableList.setValue(searchList)
    }

    fun getSearchResults(query: String) {
        addDefaultOptions(query)

        val callbackAlbum: Callback<SearchResponse?> =
            object : Callback<SearchResponse?> {
                override fun onResponse(
                    call: Call<SearchResponse?>,
                    response: Response<SearchResponse?>
                ) {
                    if (response.isSuccessful) {
                        try {
                            val albumList: List<Album>? = response.body()?.results?.albummatches?.album
                            for (album in albumList!!) {
                                var thumb = ""
                                for (image in album.image!!) {
                                    if (image.size.equals("small"))
                                        thumb = image.text.toString()
                                }

                                val searchListItem = SearchListItem(
                                    thumb,
                                    album.name,
                                    album,
                                    null,
                                    null,
                                    SearchItemType.ALBUM,
                                    query
                                )

                                searchList.add(searchListItem)
                                searchMutableList.setValue(searchList)
                            }
                        }catch (e: Exception){
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                    Log.e(TAG + "Album", t.message, t)
                }
            }
        val callbackSong: Callback<SearchResponse?> =
            object : Callback<SearchResponse?> {
                override fun onResponse(
                    call: Call<SearchResponse?>,
                    response: Response<SearchResponse?>
                ) {
                    if (response.isSuccessful) {
                        try {
                            val songList: List<Track>? = response.body()?.results?.trackmatches?.track
                            for (song in songList!!) {
                                var thumb = ""
                                for (image in song.image!!) {
                                    if (image.size.equals("small"))
                                        thumb = image.text.toString()
                                }

                                val searchListItem = SearchListItem(
                                    thumb,
                                    song.name,
                                    null,
                                    null,
                                    song,
                                    SearchItemType.SONG,
                                    query
                                )

                                searchList.add(searchListItem)
                                searchMutableList.value = searchList
                            }
                        }catch (e: Exception){
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                    Log.e(TAG + "Track", t.message, t)
                }
            }
        val callbackArtist: Callback<SearchResponse?> =
            object : Callback<SearchResponse?> {
                override fun onResponse(
                    call: Call<SearchResponse?>,
                    response: Response<SearchResponse?>
                ) {
                    if (response.isSuccessful) {
                        try {
                            val artistList: List<Artist>? =
                                response.body()?.results?.artistmatches?.artist
                            for (artist in artistList!!) {
                                var thumb = ""
                                for (image in artist.image!!) {
                                    if (image.size.equals("small"))
                                        thumb = image.text.toString()
                                }

                                val searchListItem = SearchListItem(
                                    thumb,
                                    artist.name,
                                    null,
                                    artist,
                                    null,
                                    SearchItemType.ARTIST,
                                    query
                                )

                                searchList.add(searchListItem)
                                searchMutableList.setValue(searchList)
                            }
                        }catch (e: Exception){
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {
                    Log.e(TAG + "Artist", t.message, t)
                }
            }

        RetrofitService().getApi()!!.search(
            "album.search",
            null,
            CHandler().getAPIKey(),
            "json",
            query,
            null,
            3
        )!!.enqueue(callbackAlbum)

        RetrofitService().getApi()!!.search(
            "track.search",
            null,
            CHandler().getAPIKey(),
            "json",
            null,
            query,
            3
        )!!.enqueue(callbackSong)

        RetrofitService().getApi()!!.search(
            "artist.search",
            query,
            CHandler().getAPIKey(),
            "json",
            null,
            null,
            3
        )!!.enqueue(callbackArtist)
    }
}