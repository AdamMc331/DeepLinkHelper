package com.adammcneilly.deeplinkhelper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private val _deepLinks = MutableLiveData<List<DeepLink>>()
    val deepLinks: LiveData<List<DeepLink>> = _deepLinks

    fun loadData() {
        val dummyURIs = listOf(DeepLink("myapp://page1"), DeepLink("myapp://page2"))
        _deepLinks.value = dummyURIs
    }
}