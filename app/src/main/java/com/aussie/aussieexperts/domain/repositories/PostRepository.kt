package com.aussie.aussieexperts.domain.repositories

import com.aussie.aussieexperts.data.Post
import com.aussie.aussieexperts.data.Story
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await



class PostRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    suspend fun getPosts(): List<Post> {
        return try {
            val snapshot = firestore.collection("post")
                .orderBy("sent", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get()
                .await()
            snapshot.documents.mapNotNull { doc ->
                doc.toObject(Post::class.java)?.copy(id = doc.id)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}