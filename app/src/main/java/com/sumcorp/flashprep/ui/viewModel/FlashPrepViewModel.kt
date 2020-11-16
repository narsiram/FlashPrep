package com.sumcorp.flashprep.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumcorp.flashprep.data.local.entity.RecentSearchData
import com.sumcorp.flashprep.data.model.ResultListData
import com.sumcorp.flashprep.data.model.SuperheroesListResponse
import com.sumcorp.flashprep.data.remote.NetworkHelper
import com.sumcorp.flashprep.data.repo.FlashPrepRepository

class FlashPrepViewModel(
    val repository: FlashPrepRepository,
    private var networkHelper: NetworkHelper,

    ) : ViewModel() {

    companion object {
        const val API_KEY = "3450773898347401"
    }

    //superhero list server
    private val _superHeroListResponse = MutableLiveData<SuperheroesListResponse>()

    val superheroesListResponse: LiveData<SuperheroesListResponse>
        get() = _superHeroListResponse


    //superhero data server
    private val _superHeroResponse = MutableLiveData<ResultListData>()

    val superheroesResponse: LiveData<ResultListData>
        get() = _superHeroResponse


    //recent search list local
    private val recentSearchList = MutableLiveData<ArrayList<RecentSearchData>>()

    val searchList: LiveData<ArrayList<RecentSearchData>>
        get() = recentSearchList


    private val _errorResponse = MutableLiveData<String>()

    val errorResponse: LiveData<String>
        get() = _errorResponse


    fun getSuperheroesList(text: String) {
        updateData(text)
        if (networkHelper.isNetworkConnected()) {
            repository.getSuperheroesList(API_KEY, text, { superheroesListResponse ->
                _superHeroListResponse.postValue(superheroesListResponse)
            }, {
                _errorResponse.postValue(it)
            })
        }
    }

    fun getRecentSearches() {
        repository.getRecentSearch {
            if (it != null)
                recentSearchList.postValue(it)
            else
                _errorResponse.postValue("")
        }
    }

    fun updateData(text: String) {
        repository.insertRecentSearches(text)
    }

    fun getSuperHero(id: String) {
        if (networkHelper.isNetworkConnected()) {
            repository.getSuperHero(API_KEY, id, {
                _superHeroResponse.postValue(it)
            }, {
                _errorResponse.postValue(it)

            })
        }
    }

}