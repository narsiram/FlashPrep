package com.sumcorp.flashprep.data.model

import com.google.gson.annotations.SerializedName

data class SuperheroesListResponse(
@SerializedName("results")
var resultList: ArrayList<ResultListData>


)