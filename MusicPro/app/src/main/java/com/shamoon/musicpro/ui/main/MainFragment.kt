package com.shamoon.musicpro.ui.main

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shamoon.musicpro.R
import com.shamoon.musicpro.data.types.SearchItemType
import com.shamoon.musicpro.data.types.SearchListItem
import com.shamoon.musicpro.databinding.MainFragmentBinding
import com.shamoon.musicpro.ui.GetInfoListener


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var mListener: SearchFragmentListener
    private lateinit var getInfoListener: GetInfoListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        activity!!.title = "Search"
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupBindings(savedInstanceState)
    }

    private fun setupBindings(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        if (savedInstanceState == null)
            viewModel.init()


        context?.let { viewModel.setBinding(binding, it) }
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())

        val observer = Observer<ArrayList<SearchListItem>> { searchList ->
            // Update the UI
            viewModel.setSearchListInAdapter(searchList)
        }

        viewModel.getSearchList().observe(viewLifecycleOwner, observer)

        setupListClick();
    }

    private fun setupListClick() {
        val observer = Observer<SearchListItem> { item ->
            if (item != null) {
                when (item.type) {
                    SearchItemType.SEARCH_BY_ARTIST -> mListener.onArtistSearchClick(item.query.toString())
                    SearchItemType.SEARCH_BY_SONG -> mListener.onSongSearchClick(item.query.toString())
                    SearchItemType.SEARCH_BY_ALBUM -> mListener.onAlbumSearchClick(item.query.toString())
                    SearchItemType.ALBUM -> item.album?.let { getInfoListener.onAlbumInfoClick(it) }
                    SearchItemType.ARTIST -> item.artist?.let { getInfoListener.onArtistInfoClick(it) }
                    SearchItemType.SONG -> item.song?.let { getInfoListener.onSongInfoClick(it) }
                }
            }
        }
        viewModel.selected.observe(viewLifecycleOwner, observer)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchFragmentListener) {
            mListener = context as SearchFragmentListener
        }
        if (context is GetInfoListener) {
            getInfoListener = context as GetInfoListener
        }
    }

    interface SearchFragmentListener {
        fun onAlbumSearchClick(name: String)
        fun onSongSearchClick(name: String)
        fun onArtistSearchClick(name: String)
    }

}
