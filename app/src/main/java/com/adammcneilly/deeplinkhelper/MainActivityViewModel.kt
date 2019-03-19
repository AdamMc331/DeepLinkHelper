package com.adammcneilly.deeplinkhelper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.deeplinkhelper.data.DLRepository

class MainActivityViewModel(
    private val repository: DLRepository
) : ViewModel() {

    val deepLinks: LiveData<List<DeepLink>> = repository.getAll()

    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String> = _inputText

    fun deepLinkClicked(deepLink: DeepLink) {
        _inputText.value = deepLink.uri
    }

    fun deepLinkSent(uri: String) {
        val deepLink = DeepLink(uri)
        repository.insert(deepLink)
        _inputText.value = ""
    }
}