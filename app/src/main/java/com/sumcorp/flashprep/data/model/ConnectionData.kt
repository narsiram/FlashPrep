package com.sumcorp.flashprep.data.model

import com.google.gson.annotations.SerializedName

data class ConnectionData(
    @SerializedName("group-affiliation")
    var description: String
)