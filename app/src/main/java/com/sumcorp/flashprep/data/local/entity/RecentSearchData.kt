package com.sumcorp.flashprep.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.sumcorp.flashprep.data.local.typeConverter.ListTypeConverter

@Entity(tableName = "table_recent_search")
data class RecentSearchData(

    /* @PrimaryKey(autoGenerate = true)
     val id: Int,*/
    @PrimaryKey
    @ColumnInfo(name = "recent_search")
    @TypeConverters(ListTypeConverter::class)
    var recentSearch: String
)