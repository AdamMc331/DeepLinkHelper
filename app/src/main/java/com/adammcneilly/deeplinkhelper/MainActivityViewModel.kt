package com.adammcneilly.deeplinkhelper

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.deeplinkhelper.data.DLRepository

class MainActivityViewModel(
    private val repository: DLRepository
) : ViewModel() {

    val deepLinks: LiveData<List<DeepLink>> = repository.getAll()

    fun deepLinkClicked(deepLink: DeepLink) {
        Log.d("ARM", "Deep link clicked: $deepLink")
    }

    fun deepLinkSent(deepLink: DeepLink) {
        repository.insert(deepLink)
    }
}