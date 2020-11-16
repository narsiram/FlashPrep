package com.sumcorp.flashprep.data.local.typeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sumcorp.flashprep.data.local.entity.RecentSearchData
import java.lang.reflect.Type

object ListTypeConverter {
    @TypeConverter
    @JvmStatic
    fun fromName(value: RecentSearchData) = Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun toLogin(value: String): ArrayList<RecentSearchData> {
        val listType: Type = object : TypeToken<ArrayList<RecentSearchData>>() {}.type
        return Gson().fromJson(value, listType)
    }


}