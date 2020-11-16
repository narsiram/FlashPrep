package com.sumcorp.flashprep.data.repo

import com.sumcorp.flashprep.data.local.dao.FlashPrepDao
import com.sumcorp.flashprep.data.local.entity.RecentSearchData
import com.sumcorp.flashprep.data.model.ResultListData
import com.sumcorp.flashprep.data.model.SuperheroesListResponse
import com.sumcorp.flashprep.data.remote.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoImplementation(
    val dao: FlashPrepDao,
    val apiService: ApiService
) : FlashPrepRepository {

    override fun getSuperheroesList(
        apiKey: String,
        searchText: String,
        onSuccess: (response: SuperheroesListResponse) -> Unit,
        onError: (errorMessage: String) -> Unit
    ) {

        val call = apiService.getSuperheroes(apiKey, searchText)

        call.enqueue(object : Callback<SuperheroesListResponse> {
            override fun onResponse(
                call: Call<SuperheroesListResponse>,
                response: Response<SuperheroesListResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val responseData = response.body()

                    Thread {
                        onSuccess(responseData!!)

                    }.start()

                } else {
                    onError(response.message())
                }

            }

            override fun onFailure(call: Call<SuperheroesListResponse>, t: Throwable) {
                onError("Please try again after some time")
            }

        })
    }

    override fun getSuperHero(
        apiKey: String,
        id: String,
        onSuccess: (response: ResultListData) -> Unit,
        onError: (errorMessage: String) -> Unit
    ) {
        val call = apiService.getSuperhero(apiKey, id)

        call.enqueue(object : Callback<ResultListData> {
            override fun onResponse(
                call: Call<ResultListData>,
                response: Response<ResultListData>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val responseData = response.body()

                    Thread {
                        onSuccess(responseData!!)

                    }.start()

                } else {
                    onError(response.message())
                }
            }

            override fun onFailure(call: Call<ResultListData>, t: Throwable) {
                onError("Please try again after some time")

            }

        })
    }

    override fun getRecentSearch(onSuccess: (recentSearchList: ArrayList<RecentSearchData>?) -> Unit) {
        Thread {
            onSuccess(dao.getRecentSearches() as ArrayList<RecentSearchData>)
        }.start()
    }

    override fun insertRecentSearches(text: String) {
        Thread {
            var recentSearchData = RecentSearchData(text)
            dao.insertRecentSearch(recentSearchData)
        }.start()
    }


}