package com.shamoon.musicpro.ui.song_search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shamoon.musicpro.R
import com.shamoon.musicpro.data.api_model.Track
import com.shamoon.musicpro.databinding.SearchBySongFragmentBinding
import com.shamoon.musicpro.ui.GetInfoListener
import com.shamoon.musicpro.ui.artist_search.SearchByArtistFragment

class SearchBySongFragment : Fragment() {

    companion object {
        fun newInstance() = SearchBySongFragment()

        @JvmStatic
        fun newInstance(name: String) = SearchBySongFragment().apply {
            arguments = Bundle().apply {
                putString("name", name)
            }
        }
    }

    private lateinit var viewModel: SearchBySongViewModel
    private lateinit var name: String
    private lateinit var binding: SearchBySongFragmentBinding
    private lateinit var mListener: GetInfoListener


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.search_by_song_fragment, container, false)
        activity!!.title = "Songs"
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupBindings(savedInstanceState)
    }

    private fun setupBindings(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(SearchBySongViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.init();
        }
        binding.setModel(viewModel)
        setupListUpdate();
    }

    private fun setupListUpdate() {
        viewModel.loading!!.set(View.VISIBLE)
        viewModel.fetchList(name)

        val observer = Observer<ArrayList<Track>> { categories ->
            // Update the UI, in this case, a TextView.
            viewModel.loading!!.set(View.GONE)
            if (categories.size == 0) {
                viewModel.showEmpty!!.set(View.VISIBLE)
            } else {
                viewModel.showEmpty!!.set(View.GONE)
                viewModel.setSongInAdapter(categories)
            }
        }

        viewModel.getSongs().observe(viewLifecycleOwner, observer)

        setupListClick();
    }

    private fun setupListClick() {
        val observer = Observer<Track> { track ->
            if (track != null) {
                mListener.onSongInfoClick(track)
            }
        }
        viewModel.selected.observe(viewLifecycleOwner, observer)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is GetInfoListener) {
            mListener = context as GetInfoListener
        }

        arguments?.getString("name")?.let {
            name = it
        }
    }

}