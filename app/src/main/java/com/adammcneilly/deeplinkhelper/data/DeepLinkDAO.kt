package com.adammcneilly.deeplinkhelper.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adammcneilly.deeplinkhelper.DeepLink

@Dao
interface DeepLinkDAO {
    @Query("SELECT * FROM DeepLink ORDER BY lastTimeSent")
    fun getAll(): LiveData<List<DeepLink>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(deepLink: DeepLink): Long
}