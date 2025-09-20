package com.aussie.aussieexperts.domain.repositories

import com.aussie.aussieexperts.data.Story
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class StoryRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    private var lastVisibleSnapshot: DocumentSnapshot? = null


    suspend fun getStories(limit: Long): List<Story> {

        return try {
            var query = firestore.collection("status")
                .orderBy("sent", Query.Direction.DESCENDING)
                .limit(limit)

            lastVisibleSnapshot?.let {
                query = query.startAfter(it)
            }

            val snapshot = query.get().await()

            lastVisibleSnapshot = snapshot.documents.lastOrNull()

            snapshot.documents.mapNotNull { doc ->
                doc.toObject(Story::class.java)?.copy(id = doc.id)
            }
        } catch (e: Exception) {
            emptyList<Story>()

        }
    }

    fun resetPagination() {
        lastVisibleSnapshot = null
    }
}