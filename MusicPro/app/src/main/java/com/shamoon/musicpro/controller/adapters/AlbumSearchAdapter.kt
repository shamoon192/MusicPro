package com.shamoon.musicpro.controller.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shamoon.musicpro.BR
import com.shamoon.musicpro.data.api_model.Album
import com.shamoon.musicpro.databinding.AlbumItemBinding

import com.shamoon.musicpro.ui.album_search.SearchByAlbumViewModel

class AlbumSearchAdapter : RecyclerView.Adapter<AlbumSearchAdapter.Holder> {
    private var layoutId: Int
    private var viewModel: SearchByAlbumViewModel
    private  var searchList: ArrayList<Album> = mutableListOf<Album>() as ArrayList<Album>


    constructor(
        @LayoutRes layoutId: Int,
        viewModel: SearchByAlbumViewModel?
    ) {
        this.layoutId = layoutId
        this.viewModel = viewModel!!
    }

    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<AlbumItemBinding>(
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

    fun setSearchList(searchList: ArrayList<Album>) {
        this.searchList.clear()
        this.searchList.addAll(searchList)
    }


    class Holder : RecyclerView.ViewHolder {
        var binding: AlbumItemBinding

        constructor(binding: AlbumItemBinding) : super(binding.root) {
            this.binding = binding
        }

        fun bind(viewModel: SearchByAlbumViewModel?, position: Int?) {

            binding.setVariable(BR.position, position)
            binding.setVariable(BR.viewModel, viewModel)
            binding.executePendingBindings()
        }
    }
}