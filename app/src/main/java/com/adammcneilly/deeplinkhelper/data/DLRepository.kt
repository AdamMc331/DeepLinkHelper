package com.adammcneilly.deeplinkhelper.data

import androidx.lifecycle.LiveData
import com.adammcneilly.deeplinkhelper.DeepLink

class DLRepository(private val database: DLDatabase) {
    fun getAll(): LiveData<List<DeepLink>> {
        return database.deepLinkDao().getAll()
    }

    fun insert(deepLink: DeepLink): Long {
        return database.deepLinkDao().insert(deepLink)
    }
}
