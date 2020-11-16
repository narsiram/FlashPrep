package com.sumcorp.flashprep.data.model

import com.google.gson.annotations.SerializedName

data class AppearanceData(
    @SerializedName("gender")
    var gender: String,
    @SerializedName("eye-color")
    var eyeColor: String,
    @SerializedName("hair-color")
    var hairColor: String,
    @SerializedName("race")
    var race: String,
    @SerializedName("height")
    var height: ArrayList<String>
)
