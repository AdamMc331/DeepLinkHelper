package com.adammcneilly.deeplinkhelper.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adammcneilly.deeplinkhelper.DeepLink

@Database(entities = [DeepLink::class], version = 1)
abstract class DLDatabase : RoomDatabase() {
    abstract fun deepLinkDao(): DeepLinkDAO

    companion object {
        private var INSTANCE: DLDatabase? = null

        fun getInMemoryDatabase(context: Context): DLDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    DLDatabase::class.java,
                    "deep-link-helper.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }

            return INSTANCE!!
        }
    }
}
