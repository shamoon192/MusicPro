package com.shamoon.musicpro.ui.info

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.shamoon.musicpro.R
import com.shamoon.musicpro.data.api_model.Album
import com.shamoon.musicpro.data.api_model.Artist
import com.shamoon.musicpro.data.api_model.Track
import com.shamoon.musicpro.data.types.SearchItemType
import com.shamoon.musicpro.databinding.InfoFragmentBinding

class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()

        @JvmStatic
        fun newInstance(album: Album) = InfoFragment().apply {
            arguments = Bundle().apply {
                putSerializable("album", album)
                putSerializable("type", SearchItemType.ALBUM)
            }
        }

        @JvmStatic
        fun newInstance(artist: Artist) = InfoFragment().apply {
            arguments = Bundle().apply {
                putSerializable("artist", artist)
                putSerializable("type", SearchItemType.ARTIST)
            }
        }

        @JvmStatic
        fun newInstance(song: Track) = InfoFragment().apply {
            arguments = Bundle().apply {
                putSerializable("song", song)
                putSerializable("type", SearchItemType.SONG)
            }
        }
    }
    private lateinit var album: Album
    private lateinit var artist: Artist
    private lateinit var song: Track
    private lateinit var type: SearchItemType
    private lateinit var binding: InfoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.info_fragment, container, false)
        activity!!.title = "Info"

        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupBindings(savedInstanceState)
    }
    private fun setupBindings(savedInstanceState: Bundle?) {

        if (type == SearchItemType.ALBUM){
            binding.tvLink.setText(album.name.toString())
            binding.tvListeners.setText(album.artist.toString())
            binding.tvLink.setText(album.url.toString())
            context?.let { Glide.with(it).load(album.image!!.get(album.image!!.size-1).text).into(binding.imageView) }
        }else if (type == SearchItemType.ARTIST){
            binding.tvLink.setText(artist.name.toString())
            binding.tvListeners.setText(artist.listeners.toString())
            binding.tvLink.setText(artist.url.toString())
            context?.let { Glide.with(it).load(artist.image!!.get(artist.image!!.size-1).text).into(binding.imageView) }
        }else if (type == SearchItemType.SONG){
            binding.tvLink.setText(song.name.toString())
            binding.tvListeners.setText(song.artist.toString())
            binding.tvLink.setText(song.url.toString())
            context?.let { Glide.with(it).load(song.image!!.get(song.image!!.size-1).text).into(binding.imageView) }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        arguments?.getSerializable("album")?.let {
            album = it as Album
        }
        arguments?.getSerializable("artist")?.let {
            artist = it as Artist
        }
        arguments?.getSerializable("song")?.let {
            song = it as Track
        }

        arguments?.getSerializable("type")?.let {
            type = it as SearchItemType
        }
    }

}