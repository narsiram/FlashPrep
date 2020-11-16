package com.sumcorp.flashprep.data.repo

import com.sumcorp.flashprep.data.local.entity.RecentSearchData
import com.sumcorp.flashprep.data.model.ResultListData
import com.sumcorp.flashprep.data.model.SuperheroesListResponse

interface FlashPrepRepository {

    fun getSuperheroesList(
        apiKey: String,
        searchText: String,
        onSuccess: (response: SuperheroesListResponse) -> Unit,
        onError: (errorMessage: String) -> Unit
    )

    fun getSuperHero(
        apiKey: String,
        id: String,
        onSuccess: (response: ResultListData) -> Unit,
        onError: (errorMessage: String) -> Unit
    )

    fun getRecentSearch(onSuccess: (recentSearchList: ArrayList<RecentSearchData>?) -> Unit)
    fun insertRecentSearches(text: String)
}