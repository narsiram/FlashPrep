package com.sumcorp.flashprep.data.local.database

import android.content.Context
import androidx.room.*
import com.sumcorp.flashprep.data.local.dao.FlashPrepDao
import com.sumcorp.flashprep.data.local.entity.RecentSearchData
import com.sumcorp.flashprep.data.local.typeConverter.ListTypeConverter


@Database(entities = [RecentSearchData::class], version = 1)
@TypeConverters(ListTypeConverter::class)
abstract class FlashPrepDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "flashprep-app"

        @Volatile
        private var INSTANCE: FlashPrepDatabase? = null

        fun getInstance(context: Context): FlashPrepDatabase {
            synchronized(this) {
                var instace = INSTANCE

                if (instace == null) {
                    instace = Room.databaseBuilder(
                        context.applicationContext, FlashPrepDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }

                INSTANCE = instace
                return instace

            }

        }
    }

    abstract fun getDao(): FlashPrepDao
}