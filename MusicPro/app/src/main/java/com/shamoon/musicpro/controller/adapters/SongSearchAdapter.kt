package com.shamoon.musicpro.controller.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shamoon.musicpro.BR
import com.shamoon.musicpro.data.api_model.Track
import com.shamoon.musicpro.databinding.SongItemBinding
import com.shamoon.musicpro.ui.song_search.SearchBySongViewModel

class SongSearchAdapter : RecyclerView.Adapter<SongSearchAdapter.Holder> {
    private var layoutId: Int
    private var viewModel: SearchBySongViewModel
    private var searchList: ArrayList<Track> = mutableListOf<Track>() as ArrayList<Track>


    constructor(
        @LayoutRes layoutId: Int,
        viewModel: SearchBySongViewModel?
    ) {
        this.layoutId = layoutId
        this.viewModel = viewModel!!
    }

    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<SongItemBinding>(
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

    fun setSearchList(searchList: ArrayList<Track>) {
        this.searchList.clear()
        this.searchList.addAll(searchList)
    }


    class Holder : RecyclerView.ViewHolder {
        var binding: SongItemBinding

        constructor(binding: SongItemBinding) : super(binding.root) {
            this.binding = binding
        }

        fun bind(viewModel: SearchBySongViewModel?, position: Int?) {

            binding.setVariable(BR.position, position)
            binding.setVariable(BR.viewModel, viewModel)
            binding.executePendingBindings()
        }
    }
}