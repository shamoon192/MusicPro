package com.shamoon.musicpro.ui.artist_search

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shamoon.musicpro.R
import com.shamoon.musicpro.controller.adapters.ArtistSearchAdapter
import com.shamoon.musicpro.data.api_model.Artist
import com.shamoon.musicpro.data.repositories.ArtistRepository

class SearchByArtistViewModel : ViewModel() {
    private lateinit var artistRepository: ArtistRepository
    private lateinit var adapter: ArtistSearchAdapter
    lateinit var loading: ObservableInt
    lateinit var showEmpty: ObservableInt
    lateinit var selected: MutableLiveData<Artist>


    fun init() {
        artistRepository =
            ArtistRepository()
        adapter = ArtistSearchAdapter(R.layout.artist_item, this)
        loading = ObservableInt(View.GONE)
        showEmpty = ObservableInt(View.GONE)
        selected = MutableLiveData()
    }

    fun fetchList(artist: String) {
        artistRepository.getArtist(artist)
    }

    fun getArtist(): MutableLiveData<ArrayList<Artist>> {
        return artistRepository.artists
    }

    fun getAdapter(): ArtistSearchAdapter? {
        return adapter
    }

    fun setArtistInAdapter(categories: ArrayList<Artist>) {
        adapter!!.setSearchList(categories)
        adapter!!.notifyDataSetChanged()
    }

    fun onItemClick(index: Int?) {
        val db: Artist? = getArtistAt(index)
        selected!!.setValue(db)
    }

    fun getArtistAt(index: Int?): Artist? {
        return if (artistRepository.artists
                .getValue() != null && index != null && artistRepository.artists
                .getValue()!!.size > index
        ) {
            artistRepository.artists.getValue()!!.get(index)
        } else null
    }
}