package com.adammcneilly.deeplinkhelper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.deeplinkhelper.data.DLRepository
import org.greenrobot.eventbus.EventBus

class MainActivityViewModel(
    private val repository: DLRepository
) : ViewModel() {

    val deepLinks: LiveData<List<DeepLink>> = repository.getAll()

    private val _inputText = MutableLiveData<String>()
    val inputText: LiveData<String> = _inputText

    private val _inputErrorRes = MutableLiveData<Int>()
    val inputErrorRes: LiveData<Int> = _inputErrorRes

    fun deepLinkClicked(deepLink: DeepLink) {
        _inputText.value = deepLink.uri
    }

    fun processDeepLink(uri: String) {
        if (uri.isNotEmpty()) {
            val deepLink = DeepLink(uri)
            repository.insert(deepLink)
            EventBus.getDefault().post(deepLink)
            _inputErrorRes.value = null
        } else {
            _inputErrorRes.value = R.string.invalid_uri
        }
    }
}
