package com.aussie.aussieexperts.data

import androidx.compose.runtime.Immutable

@Immutable
data class Post(
    val id: String = "",
    val image: String = "",
    val read: String = "",
    val senderId: String = "",
    val senderImage: String = "",
    val initials: String = "",
    val senderName: String = "",
    val sent: String = "",
    val text: String = "",
    val type: String = "",
    val likes: Int = 0,
    val likedBy: List<String> = emptyList()
)
