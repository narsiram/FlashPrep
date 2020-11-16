package com.sumcorp.flashprep.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sumcorp.flashprep.data.local.entity.RecentSearchData

@Dao
interface FlashPrepDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentSearch(searchString: RecentSearchData)

    @Query("select * from table_recent_search")
    fun getRecentSearches(): List<RecentSearchData>
}