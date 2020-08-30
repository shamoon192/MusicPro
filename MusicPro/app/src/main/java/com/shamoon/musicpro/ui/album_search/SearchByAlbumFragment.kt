package com.shamoon.musicpro.ui.album_search

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shamoon.musicpro.R
import com.shamoon.musicpro.data.api_model.Album
import com.shamoon.musicpro.databinding.SearchByAlbumFragmentBinding
import com.shamoon.musicpro.ui.GetInfoListener

class SearchByAlbumFragment : Fragment() {

    companion object {
        fun newInstance() = SearchByAlbumFragment()
        @JvmStatic
        fun newInstance(name: String) = SearchByAlbumFragment().apply {
            arguments = Bundle().apply {
                putString("name", name)
            }
        }
    }
    private lateinit var name: String
    private lateinit var viewModel: SearchByAlbumViewModel
    private lateinit var binding: SearchByAlbumFragmentBinding

    private lateinit var mListener: GetInfoListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_by_album_fragment, container, false)
        activity!!.title = "Albums"
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupBindings(savedInstanceState)
    }

    private fun setupBindings(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(SearchByAlbumViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.init();
        }
        binding.setModel(viewModel)
        setupListUpdate();
    }

    private fun setupListUpdate() {
        viewModel.loading!!.set(View.VISIBLE)
        viewModel.fetchList(name)

        val observer = Observer<ArrayList<Album>> { categories ->
            // Update the UI, in this case, a TextView.
            viewModel.loading!!.set(View.GONE)
            if (categories.size == 0) {
                viewModel.showEmpty!!.set(View.VISIBLE)
            } else {
                viewModel.showEmpty!!.set(View.GONE)
                viewModel.setAlbumsInAdapter(categories)
            }
        }

        viewModel.getAlbums().observe(viewLifecycleOwner, observer)

        setupListClick();
    }

    private fun setupListClick() {
        val observer = Observer<Album> { album ->
            if (album != null) {
                mListener.onAlbumInfoClick(album)
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