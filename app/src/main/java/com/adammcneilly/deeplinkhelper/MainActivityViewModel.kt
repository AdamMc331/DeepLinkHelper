package com.adammcneilly.deeplinkhelper

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.deeplinkhelper.data.DLRepository

class MainActivityViewModel(
    repository: DLRepository
) : ViewModel() {

    val deepLinks: LiveData<List<DeepLink>> = repository.getAll()

}