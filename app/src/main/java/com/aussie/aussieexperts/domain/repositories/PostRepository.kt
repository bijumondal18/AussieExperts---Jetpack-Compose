package com.aussie.aussieexperts.domain.repositories

import com.aussie.aussieexperts.data.Post
import com.aussie.aussieexperts.data.Story
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await



class PostRepository(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    private var lastVisibleSnapshot: DocumentSnapshot? = null


    suspend fun getPosts(limit: Long): List<Post> {
        return try {
            var query = firestore.collection("post")
                .orderBy("sent", Query.Direction.DESCENDING)
                .limit(limit)

            lastVisibleSnapshot?.let {
                query = query.startAfter(it)
            }

            val snapshot = query.get().await()

            lastVisibleSnapshot = snapshot.documents.lastOrNull()

            snapshot.documents.mapNotNull { doc ->
                doc.toObject(Post::class.java)?.copy(id = doc.id)
            }

        } catch (e: Exception) {
            emptyList()
        }
    }

    fun resetPagination() {
        lastVisibleSnapshot = null
    }

}