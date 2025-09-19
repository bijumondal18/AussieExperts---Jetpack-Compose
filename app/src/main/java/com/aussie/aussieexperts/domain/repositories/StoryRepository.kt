package com.aussie.aussieexperts.domain.repositories

import com.aussie.aussieexperts.data.Story
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class StoryRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    suspend fun getStories(): List<Story> {
        return try {
            val snapshot = firestore.collection("status")
                .get()
                .await()
            snapshot.documents.mapNotNull { doc ->
                doc.toObject(Story::class.java)?.copy(id = doc.id)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}