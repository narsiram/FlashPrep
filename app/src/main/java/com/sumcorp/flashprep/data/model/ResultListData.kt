package com.sumcorp.flashprep.data.model

import com.google.gson.annotations.SerializedName

data class ResultListData(

    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("image")
    var profileUrl: ImageData,
    @SerializedName("appearance")
    var appearance: AppearanceData,
    @SerializedName("connections")
    var connections:ConnectionData

)
