package com.sumcorp.flashprep.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sumcorp.flashprep.data.remote.NetworkHelper
import com.sumcorp.flashprep.data.repo.FlashPrepRepository

@Suppress("UNCHECKED_CAST")
class FlashPrepViewModelFactory(
    val repository: FlashPrepRepository,
    private var networkHelper: NetworkHelper

    ):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FlashPrepViewModel(repository, networkHelper) as T
    }
}