package com.sumcorp.flashprep.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumcorp.flashprep.R
import com.sumcorp.flashprep.data.local.database.FlashPrepDatabase
import com.sumcorp.flashprep.data.model.ResultListData
import com.sumcorp.flashprep.data.remote.NetworkHelper
import com.sumcorp.flashprep.data.remote.RetrofitBuilder
import com.sumcorp.flashprep.data.repo.RepoImplementation
import com.sumcorp.flashprep.ui.base.BaseActivity
import com.sumcorp.flashprep.ui.detail.DetailedResultActivity
import com.sumcorp.flashprep.ui.viewModel.FlashPrepViewModel
import com.sumcorp.flashprep.ui.viewModel.FlashPrepViewModelFactory
import kotlinx.android.synthetic.main.activity_search_list_data.*
import java.util.ArrayList

class ResultListDataActivity : BaseActivity(), ResultListAdapter.OnClickEvent {

    private lateinit var viewModel: FlashPrepViewModel
    private lateinit var adapter: ResultListAdapter
    private var text: String? = null
    private var dataList: ArrayList<ResultListData>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_list_data)

        text = intent.getStringExtra("searchString")
        setUpRecyclerView()

        setupViewModel()
        observeViewModel()

        initToolbar(getString(R.string.results))


    }

    private fun observeViewModel() {
        viewModel.superheroesListResponse.observe(this, Observer {
            setDataToAdapter(it.resultList)
        })

        viewModel.errorResponse.observe(this, Observer {
            showError(lyt, it)
        })
    }

    private fun setDataToAdapter(resultList: ArrayList<ResultListData>) {
        if (resultList != null && resultList.size > 0) {
            this.dataList = resultList

            adapter.setData(resultList)
        } else {
            showEmptyState()
        }
    }

    private fun showEmptyState() {
        lytEmpty.visibility = View.VISIBLE
        recycleView.visibility = View.GONE
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

        viewModel.getSuperheroesList(text!!)
    }

    private fun setUpRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        recycleView.layoutManager = linearLayoutManager
        recycleView.itemAnimator = DefaultItemAnimator()
        recycleView.setHasFixedSize(true)
        adapter = ResultListAdapter(this)
        val dividerItemDecoration =
            DividerItemDecoration(this, linearLayoutManager.getOrientation())
        recycleView.addItemDecoration(dividerItemDecoration)
        recycleView.adapter = adapter

    }


    override fun onItemClick(position: Int) {
        startActivity(
            Intent(this, DetailedResultActivity::class.java).putExtra(
                "id",
                dataList!!.get(position).id.toString()
            )
        )
    }
}