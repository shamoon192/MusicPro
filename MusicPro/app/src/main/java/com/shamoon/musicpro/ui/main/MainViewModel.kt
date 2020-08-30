package com.shamoon.musicpro.ui.main

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.shamoon.musicpro.R
import com.shamoon.musicpro.controller.Utils
import com.shamoon.musicpro.controller.adapters.SearchListAdapter
import com.shamoon.musicpro.data.repositories.SearchRepository
import com.shamoon.musicpro.data.types.SearchItemType
import com.shamoon.musicpro.data.types.SearchListItem
import com.shamoon.musicpro.databinding.MainFragmentBinding

class MainViewModel : ViewModel(), SearchView.OnQueryTextListener {
    lateinit var loading: ObservableInt
    lateinit var showEmpty: ObservableInt
    private lateinit var binding: MainFragmentBinding
    private lateinit var context: Context
    private lateinit var repository: SearchRepository
    private lateinit var adapter: SearchListAdapter

    lateinit var selected: MutableLiveData<SearchListItem>


    fun init() {
        repository = SearchRepository()
        adapter = SearchListAdapter(R.layout.search_item, this)
        loading = ObservableInt(View.GONE)
        showEmpty = ObservableInt(View.GONE)
        selected = MutableLiveData()
    }

    fun fetchSearchList(query: String) {
        repository.getSearchResults(query)
    }

    fun getSearchList(): MutableLiveData<ArrayList<SearchListItem>> {
        return repository.searchMutableList
    }

    fun getAdapter(): SearchListAdapter? {
        return adapter
    }

    fun setSearchListInAdapter(searchList: ArrayList<SearchListItem>) {
        adapter.setSearchList(searchList)
        adapter.notifyDataSetChanged()
    }

    fun onItemClick(index: Int?) {
        val db: SearchListItem? = getSearchItemAt(index)
        selected.value = db
    }

    fun getImageVisibility(type: SearchItemType?): Int {
        return if (type!! == SearchItemType.SEARCH_BY_SONG || type == SearchItemType.SEARCH_BY_ARTIST || type == SearchItemType.SEARCH_BY_ALBUM)
            View.GONE
        else
            View.VISIBLE
    }

    private fun getSearchItemAt(index: Int?): SearchListItem? {
        return if (repository.searchMutableList
                .value != null && index != null && repository.searchMutableList
                .value!!.size > index
        ) {
            repository.searchMutableList.value!![index]
        } else null
    }

    fun setBinding(binding: MainFragmentBinding, context: Context) {
        this.binding = binding
        this.context = context

        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        binding.rvSearch.layoutManager = llm
        binding.rvSearch.adapter = getAdapter()

        this.binding.searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        hideKeyboard(binding.main)
        if (context.let { Utils.isConnected(it) }) {
            fetchSearchList(query)
        } else
            showEmpty.set(View.VISIBLE)
        return true
    }

    override fun onQueryTextChange(query: String): Boolean {
        if (context.let { Utils.isConnected(it) }) {
            fetchSearchList(query)
        } else
            showEmpty.set(View.VISIBLE)
        return true
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
