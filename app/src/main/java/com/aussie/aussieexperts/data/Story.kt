package com.aussie.aussieexperts.data

import androidx.compose.runtime.Immutable

@Immutable
data class Story(
    val id: String = "",
    val sent: String = "",
    val image: String = "",
    val read: String = "",
    val senderId: String = "",
    val senderImage: String = "",
    val initials: String = "",
    val senderName: String = "",
)

