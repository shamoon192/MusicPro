package com.shamoon.musicpro.controller.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shamoon.musicpro.BR
import com.shamoon.musicpro.data.api_model.Artist
import com.shamoon.musicpro.databinding.AlbumItemBinding
import com.shamoon.musicpro.databinding.ArtistItemBinding
import com.shamoon.musicpro.databinding.ArtistItemBindingImpl
import com.shamoon.musicpro.ui.artist_search.SearchByArtistViewModel

class ArtistSearchAdapter : RecyclerView.Adapter<ArtistSearchAdapter.Holder> {
    private var layoutId: Int
    private var viewModel: SearchByArtistViewModel
    private  var searchList: ArrayList<Artist> = mutableListOf<Artist>() as ArrayList<Artist>


    constructor(
        @LayoutRes layoutId: Int,
        viewModel: SearchByArtistViewModel?
    ) {
        this.layoutId = layoutId
        this.viewModel = viewModel!!
    }

    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<ArtistItemBinding>(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(viewModel, position)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun setSearchList(searchList: ArrayList<Artist>) {
        this.searchList.clear()
        this.searchList.addAll(searchList)
    }


    class Holder : RecyclerView.ViewHolder {
        var binding: ArtistItemBinding

        constructor(binding: ArtistItemBinding) : super(binding.root) {
            this.binding = binding
        }

        fun bind(viewModel: SearchByArtistViewModel?, position: Int?) {

            binding.setVariable(BR.position, position)
            binding.setVariable(BR.viewModel, viewModel)
            binding.executePendingBindings()
        }
    }
}