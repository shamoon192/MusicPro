package com.shamoon.musicpro.ui.album_search

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shamoon.musicpro.R
import com.shamoon.musicpro.controller.adapters.AlbumSearchAdapter
import com.shamoon.musicpro.data.api_model.Album
import com.shamoon.musicpro.data.repositories.AlbumRepository

class SearchByAlbumViewModel : ViewModel() {
    private lateinit var albumRepository: AlbumRepository
    private lateinit var adapter: AlbumSearchAdapter
    lateinit var loading: ObservableInt
    lateinit var showEmpty: ObservableInt
    lateinit var selected: MutableLiveData<Album>


    fun init() {
        albumRepository =
            AlbumRepository()
        adapter = AlbumSearchAdapter(R.layout.album_item, this)
        loading = ObservableInt(View.GONE)
        showEmpty = ObservableInt(View.GONE)
        selected = MutableLiveData()
    }

    fun fetchList(album: String) {
        albumRepository.getAlbums(album)
    }

    fun getAlbums(): MutableLiveData<ArrayList<Album>> {
        return albumRepository.albums
    }

    fun getAdapter(): AlbumSearchAdapter? {
        return adapter
    }

    fun setAlbumsInAdapter(categories: ArrayList<Album>) {
        adapter!!.setSearchList(categories)
        adapter!!.notifyDataSetChanged()
    }

    fun onItemClick(index: Int?) {
        val db: Album? = getAlbumAt(index)
        selected!!.setValue(db)
    }

    fun getAlbumAt(index: Int?): Album? {
        return if (albumRepository.albums
                .getValue() != null && index != null && albumRepository.albums
                .getValue()!!.size > index
        ) {
            albumRepository.albums.getValue()!!.get(index)
        } else null
    }
}