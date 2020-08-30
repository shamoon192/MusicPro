package com.shamoon.musicpro.ui.artist_search

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
import com.shamoon.musicpro.data.api_model.Artist
import com.shamoon.musicpro.databinding.SearchByArtistFragmentBinding
import com.shamoon.musicpro.ui.GetInfoListener

class SearchByArtistFragment : Fragment() {

    companion object {
        fun newInstance() = SearchByArtistFragment()

        @JvmStatic
        fun newInstance(name: String) = SearchByArtistFragment().apply {
            arguments = Bundle().apply {
                putString("name", name)
            }
        }
    }

    private lateinit var viewModel: SearchByArtistViewModel
    private lateinit var name: String
    private lateinit var binding: SearchByArtistFragmentBinding

    private lateinit var mListener: GetInfoListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.search_by_artist_fragment, container, false)
        activity!!.title = "Artists"
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupBindings(savedInstanceState)
    }

    private fun setupBindings(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(SearchByArtistViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.init();
        }
        binding.setModel(viewModel)
        setupListUpdate();
    }

    private fun setupListUpdate() {
        viewModel.loading!!.set(View.VISIBLE)
        viewModel.fetchList(name)

        val observer = Observer<ArrayList<Artist>> { categories ->
            // Update the UI, in this case, a TextView.
            viewModel.loading!!.set(View.GONE)
            if (categories.size == 0) {
                viewModel.showEmpty!!.set(View.VISIBLE)
            } else {
                viewModel.showEmpty!!.set(View.GONE)
                viewModel.setArtistInAdapter(categories)
            }
        }

        viewModel.getArtist().observe(viewLifecycleOwner, observer)

        setupListClick();
    }

    private fun setupListClick() {
        val observer = Observer<Artist> { album ->
            if (album != null) {
                mListener.onArtistInfoClick(album)
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