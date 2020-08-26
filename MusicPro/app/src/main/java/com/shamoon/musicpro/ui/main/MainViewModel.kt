package com.shamoon.musicpro.ui.main

import android.content.Context
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.ViewModel
import com.shamoon.musicpro.controller.Utils
import com.shamoon.musicpro.data.CHandler
import com.shamoon.musicpro.databinding.MainFragmentBinding
import de.umass.lastfm.Album

class MainViewModel : ViewModel(), SearchView.OnQueryTextListener {
    private lateinit var binding: MainFragmentBinding
    private lateinit var context: Context

    fun setBinding(binding: MainFragmentBinding, context: Context){
        this.binding = binding
        this.context = context
        this.binding.searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if (context?.let { Utils.isConnected(it) }!!){
            var albumList: List<Album> = Album.search("cher", CHandler().getAPIKey()) as List<Album>
            Log.d("MainViewModel", albumList.size.toString())
        }
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (context?.let { Utils.isConnected(it) }!!){
            var albumList: List<Album> = Album.search("cher", CHandler().getAPIKey()) as List<Album>
            Log.d("MainViewModel", albumList.size.toString())
        }
        return false
    }
}
