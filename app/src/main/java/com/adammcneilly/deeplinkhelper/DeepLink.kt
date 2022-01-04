package com.adammcneilly.deeplinkhelper

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A model class representing a deep link the user can click on.
 */
@Entity
data class DeepLink(
    @PrimaryKey val uri: String = "",
    val lastTimeSent: Long = System.currentTimeMillis()
)
