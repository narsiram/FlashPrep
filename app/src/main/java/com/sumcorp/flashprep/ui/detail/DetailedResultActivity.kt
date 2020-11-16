package com.sumcorp.flashprep.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sumcorp.flashprep.R
import com.sumcorp.flashprep.data.local.database.FlashPrepDatabase
import com.sumcorp.flashprep.data.model.ResultListData
import com.sumcorp.flashprep.data.remote.NetworkHelper
import com.sumcorp.flashprep.data.remote.RetrofitBuilder
import com.sumcorp.flashprep.data.repo.RepoImplementation
import com.sumcorp.flashprep.ui.base.BaseActivity
import com.sumcorp.flashprep.ui.viewModel.FlashPrepViewModel
import com.sumcorp.flashprep.ui.viewModel.FlashPrepViewModelFactory
import kotlinx.android.synthetic.main.activity_detailed_result.*

class DetailedResultActivity : BaseActivity() {

    private lateinit var viewModel: FlashPrepViewModel
    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_result)

        id = intent.getStringExtra("id")
        setupViewModel()
        observeViewModel()
        initToolbar(getString(R.string.superhero))
    }

    private fun observeViewModel() {
        viewModel.superheroesResponse.observe(this, Observer {
            setData(it)
        })

        viewModel.errorResponse.observe(this, Observer {
            showError(lytContainer, it)
        })
    }

    private fun setData(it: ResultListData) {
        tvRaceValue.text = it.appearance.race
        tvGenderValue.text = it.appearance.gender
        tvHeightValue.text = it.appearance.height[1]
        tvName.text = it.name
        tvDescription.text = it.connections.description

        Glide.with(this)
            .load(it.profileUrl.url)
            .centerCrop()
            .placeholder(R.drawable.default_image_24)
            .into(image)
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

        viewModel.getSuperHero(id!!)
    }

}