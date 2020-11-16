package com.sumcorp.flashprep.data.remote

import com.sumcorp.flashprep.data.model.ResultListData
import com.sumcorp.flashprep.data.model.SuperheroesListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{access-token}/search/{name}")
    fun getSuperheroes(
        @Path("access-token") token: String,
        @Path("name") searchText: String
    ): Call<SuperheroesListResponse>


    @GET("{access-token}/{id}")
    fun getSuperhero(
        @Path("access-token") token: String,
        @Path("id") searchText: String
    ): Call<ResultListData>


}