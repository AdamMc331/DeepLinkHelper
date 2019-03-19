package com.adammcneilly.deeplinkhelper

/**
 * A model class representing a deep link the user can click on.
 */
data class DeepLink(
    val uri: String = "",
    val lastTimeSent: Long = System.currentTimeMillis()
)