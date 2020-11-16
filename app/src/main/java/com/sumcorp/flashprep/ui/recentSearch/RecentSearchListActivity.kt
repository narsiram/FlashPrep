package com.sumcorp.flashprep.ui.recentSearch

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumcorp.flashprep.R
import com.sumcorp.flashprep.data.local.database.FlashPrepDatabase
import com.sumcorp.flashprep.data.local.entity.RecentSearchData
import com.sumcorp.flashprep.data.remote.NetworkHelper
import com.sumcorp.flashprep.data.remote.RetrofitBuilder
import com.sumcorp.flashprep.data.repo.RepoImplementation
import com.sumcorp.flashprep.ui.base.BaseActivity
import com.sumcorp.flashprep.ui.list.ResultListDataActivity
import com.sumcorp.flashprep.ui.viewModel.FlashPrepViewModel
import com.sumcorp.flashprep.ui.viewModel.FlashPrepViewModelFactory
import kotlinx.android.synthetic.main.activity_recent_search.*
import kotlinx.android.synthetic.main.empty_state.view.*
import java.util.ArrayList

class RecentSearchListActivity : BaseActivity(), RecentSearchAdapter.OnItemClick {


    private lateinit var viewModel: FlashPrepViewModel
    private lateinit var adapter: RecentSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_search)

//        listners()
        setUpRecyclerView()
        setupSearchView()
        setupViewModel()
        observeViewModel()

        initToolbar(getString(R.string.superheroes_villians))
    }

    private fun setupSearchView() {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                itemClick(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRecentSearches()


    }

    private fun observeViewModel() {
        viewModel.searchList.observe(this, Observer {
            showEmptyState(false)
            setDataToAdapter(it)

        })

        viewModel.errorResponse.observe(this, Observer {
            showEmptyState(true)
        })
    }

    private fun showEmptyState(isEmpty: Boolean) {
        if (isEmpty) {
            lytEmptyState.lytEmptyState.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            tv.visibility = View.GONE
        } else {
            lytEmptyState.lytEmptyState.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            tv.visibility = View.VISIBLE
        }
    }

    private fun setDataToAdapter(resultList: ArrayList<RecentSearchData>) {
        if (resultList.size == 0) {
            showEmptyState(true)
        }
        adapter.sendData(resultList)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, FlashPrepViewModelFactory(
                RepoImplementation(
                    FlashPrepDatabase.getInstance(this).getDao(),
                    RetrofitBuilder.buildService()
                ),
                NetworkHelper(this)

            )
        )[FlashPrepViewModel::class.java]

    }

    private fun setUpRecyclerView() {
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        adapter = RecentSearchAdapter(this)
        val dividerItemDecoration =
            DividerItemDecoration(this, linearLayoutManager.getOrientation())
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = adapter

    }


    override fun itemClick(text: String) {
        startActivity(
            Intent(this, ResultListDataActivity::class.java)
                .putExtra("searchString", text)
        )
    }
}