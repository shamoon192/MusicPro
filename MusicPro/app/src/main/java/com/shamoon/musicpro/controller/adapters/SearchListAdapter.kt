package com.shamoon.musicpro.controller.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shamoon.musicpro.BR
import com.shamoon.musicpro.data.api_model.Album
import com.shamoon.musicpro.data.api_model.Artist
import com.shamoon.musicpro.data.api_model.Track
import com.shamoon.musicpro.data.types.SearchListItem
import com.shamoon.musicpro.databinding.SearchItemBinding
import com.shamoon.musicpro.ui.main.MainViewModel


class SearchListAdapter : RecyclerView.Adapter<SearchListAdapter.Holder> {
    private var layoutId: Int
    private var viewModel: MainViewModel
    private  var searchList: ArrayList<SearchListItem> = mutableListOf<SearchListItem>() as ArrayList<SearchListItem>


    constructor(
        @LayoutRes layoutId: Int,
        viewModel: MainViewModel?
    ) {
        this.layoutId = layoutId
        this.viewModel = viewModel!!
    }

    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = DataBindingUtil.inflate<SearchItemBinding>(
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

    fun setSearchList(searchList: ArrayList<SearchListItem>) {
        this.searchList.clear()
        this.searchList.addAll(searchList)
    }


    class Holder : RecyclerView.ViewHolder {
        var binding: SearchItemBinding

        constructor(binding: SearchItemBinding) : super(binding.root) {
            this.binding = binding
        }

        fun bind(viewModel: MainViewModel?, position: Int?) {

            binding.setVariable(BR.position, position)
            binding.setVariable(BR.viewModel, viewModel)
            binding.executePendingBindings()
        }


    }
}