package com.shamoon.musicpro.ui.song_search

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shamoon.musicpro.R
import com.shamoon.musicpro.controller.adapters.SongSearchAdapter

import com.shamoon.musicpro.data.api_model.Track
import com.shamoon.musicpro.data.repositories.SongRepository

class SearchBySongViewModel : ViewModel() {
    private lateinit var songRepository: SongRepository
    private lateinit var adapter: SongSearchAdapter
    lateinit var loading: ObservableInt
    lateinit var showEmpty: ObservableInt
    lateinit var selected: MutableLiveData<Track>


    fun init() {
        songRepository =
            SongRepository()
        adapter = SongSearchAdapter(R.layout.song_item, this)
        loading = ObservableInt(View.GONE)
        showEmpty = ObservableInt(View.GONE)
        selected = MutableLiveData()
    }

    fun fetchList(song: String) {
        songRepository.getSongs(song)
    }

    fun getSongs(): MutableLiveData<ArrayList<Track>> {
        return songRepository.songs
    }

    fun getAdapter(): SongSearchAdapter? {
        return adapter
    }

    fun setSongInAdapter(categories: ArrayList<Track>) {
        adapter!!.setSearchList(categories)
        adapter!!.notifyDataSetChanged()
    }

    fun onItemClick(index: Int?) {
        val db: Track? = getSongAt(index)
        selected!!.setValue(db)
    }

    fun getSongAt(index: Int?): Track? {
        return if (songRepository.songs
                .getValue() != null && index != null && songRepository.songs
                .getValue()!!.size > index
        ) {
            songRepository.songs.getValue()!!.get(index)
        } else null
    }
}