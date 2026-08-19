package com.tourease.app.repository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.tourease.app.models.Comment
import com.tourease.app.models.Post
import java.util.UUID

class SocialRepository {

    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val auth = FirebaseAuth.getInstance()

    val currentUserId: String get() = auth.currentUser?.uid ?: ""

    fun createPost(
        text: String,
        imageUri: Uri?,
        location: String,
        vibeTags: List<String>,
        isAnonymous: Boolean,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val userId = currentUserId
        if (userId.isEmpty()) {
            onFailure(Exception("Not logged in"))
            return
        }

        db.collection("users").document(userId).get()
            .addOnSuccessListener { userDoc ->
                val userName = userDoc.getString("username") ?: "User"
                val userPhoto = userDoc.getString("photoBase64") ?: ""

                if (imageUri != null) {
                    val imageRef = storage.reference.child("posts/${UUID.randomUUID()}.jpg")
                    imageRef.putFile(imageUri)
                        .addOnSuccessListener {
                            imageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                                savePost(userId, userName, userPhoto, text, downloadUrl.toString(), location, vibeTags, isAnonymous, onSuccess, onFailure)
                            }
                        }
                        .addOnFailureListener { onFailure(it) }
                } else {
                    savePost(userId, userName, userPhoto, text, "", location, vibeTags, isAnonymous, onSuccess, onFailure)
                }
            }
            .addOnFailureListener { onFailure(it) }
    }

    private fun savePost(
        userId: String,
        userName: String,
        userPhoto: String,
        text: String,
        imageUrl: String,
        location: String,
        vibeTags: List<String>,
        isAnonymous: Boolean,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val postRef = db.collection("posts").document()
        val post = Post(
            id = postRef.id,
            userId = userId,
            userName = if (isAnonymous) "Anonymous Traveller" else userName,
            userPhotoBase64 = if (isAnonymous) "" else userPhoto,
            text = text,
            imageUrl = imageUrl,
            location = location,
            vibeTags = vibeTags,
            isAnonymous = isAnonymous,
            fireCount = 0,
            firedBy = emptyList(),
            commentCount = 0,
            bookmarkedBy = emptyList(),
            timestamp = Timestamp.now()
        )

        postRef.set(post)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure(it) }
    }

    fun getDiscoverFeed(onResult: (List<Post>) -> Unit) {
        db.collection("posts")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(50)
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null) {
                    onResult(emptyList())
                    return@addSnapshotListener
                }
                val posts = snapshot.documents.mapNotNull { it.toObject(Post::class.java) }
                onResult(posts)
            }
    }

    fun getFollowingFeed(onResult: (List<Post>) -> Unit) {
        val userId = currentUserId
        if (userId.isEmpty()) {
            onResult(emptyList())
            return
        }

        db.collection("users").document(userId).get()
            .addOnSuccessListener { userDoc ->
                @Suppress("UNCHECKED_CAST")
                val following = userDoc.get("following") as? List<String> ?: emptyList()

                if (following.isEmpty()) {
                    onResult(emptyList())
                    return@addOnSuccessListener
                }

                val chunks = following.chunked(10)
                val allPosts = mutableListOf<Post>()
                var completedChunks = 0

                for (chunk in chunks) {
                    db.collection("posts")
                        .whereIn("userId", chunk)
                        .orderBy("timestamp", Query.Direction.DESCENDING)
                        .limit(30)
                        .get()
                        .addOnSuccessListener { snapshot ->
                            allPosts.addAll(snapshot.documents.mapNotNull { it.toObject(Post::class.java) })
                            completedChunks++
                            if (completedChunks == chunks.size) {
                                onResult(allPosts.sortedByDescending { it.timestamp })
                            }
                        }
                        .addOnFailureListener {
                            completedChunks++
                            if (completedChunks == chunks.size) {
                                onResult(allPosts.sortedByDescending { it.timestamp })
                            }
                        }
                }
            }
    }

    fun toggleFire(postId: String, onResult: (Boolean, Int) -> Unit) {
        val userId = currentUserId
        val postRef = db.collection("posts").document(postId)

        db.runTransaction { transaction ->
            val snapshot = transaction.get(postRef)
            @Suppress("UNCHECKED_CAST")
            val firedBy = snapshot.get("firedBy") as? MutableList<String> ?: mutableListOf()
            val isFired = userId in firedBy

            if (isFired) {
                firedBy.remove(userId)
            } else {
                firedBy.add(userId)
            }

            transaction.update(postRef, "firedBy", firedBy)
            transaction.update(postRef, "fireCount", firedBy.size)
            Pair(!isFired, firedBy.size)
        }.addOnSuccessListener { result ->
            onResult(result.first, result.second)
        }
    }

    fun toggleBookmark(postId: String, onResult: (Boolean) -> Unit) {
        val userId = currentUserId
        val postRef = db.collection("posts").document(postId)

        db.runTransaction { transaction ->
            val snapshot = transaction.get(postRef)
            @Suppress("UNCHECKED_CAST")
            val bookmarkedBy = snapshot.get("bookmarkedBy") as? MutableList<String> ?: mutableListOf()
            val isBookmarked = userId in bookmarkedBy

            if (isBookmarked) {
                bookmarkedBy.remove(userId)
            } else {
                bookmarkedBy.add(userId)
            }

            transaction.update(postRef, "bookmarkedBy", bookmarkedBy)
            !isBookmarked
        }.addOnSuccessListener { isNowBookmarked ->
            onResult(isNowBookmarked)
        }
    }

    fun addComment(postId: String, text: String, onSuccess: () -> Unit) {
        val userId = currentUserId

        db.collection("users").document(userId).get()
            .addOnSuccessListener { userDoc ->
                val userName = userDoc.getString("username") ?: "User"

                val commentRef = db.collection("posts").document(postId)
                    .collection("comments").document()

                val comment = Comment(
                    id = commentRef.id,
                    postId = postId,
                    userId = userId,
                    userName = userName,
                    text = text,
                    timestamp = Timestamp.now()
                )

                commentRef.set(comment).addOnSuccessListener {
                    db.collection("posts").document(postId)
                        .update("commentCount", FieldValue.increment(1))
                    onSuccess()
                }
            }
    }

    fun getComments(postId: String, onResult: (List<Comment>) -> Unit) {
        db.collection("posts").document(postId)
            .collection("comments")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, _ ->
                val comments = snapshot?.documents?.mapNotNull { it.toObject(Comment::class.java) } ?: emptyList()
                onResult(comments)
            }
    }

    fun deleteComment(postId: String, commentId: String, onSuccess: () -> Unit) {
        db.collection("posts").document(postId)
            .collection("comments").document(commentId)
            .delete()
            .addOnSuccessListener {
                db.collection("posts").document(postId)
                    .update("commentCount", FieldValue.increment(-1))
                onSuccess()
            }
    }

    fun deletePost(postId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("posts").document(postId)
            .delete()
            .addOnSuccessListener {
                db.collection("posts").document(postId)
                    .collection("comments").get()
                    .addOnSuccessListener { snapshot ->
                        val batch = db.batch()
                        snapshot.documents.forEach { batch.delete(it.reference) }
                        batch.commit()
                    }
                onSuccess()
            }
            .addOnFailureListener { onFailure(it) }
    }

    fun toggleFollow(targetUserId: String, onResult: (Boolean) -> Unit) {
        val userId = currentUserId
        val userRef = db.collection("users").document(userId)
        val targetRef = db.collection("users").document(targetUserId)

        db.runTransaction { transaction ->
            val userSnap = transaction.get(userRef)
            @Suppress("UNCHECKED_CAST")
            val following = userSnap.get("following") as? MutableList<String> ?: mutableListOf()
            val isFollowing = targetUserId in following

            if (isFollowing) {
                following.remove(targetUserId)
                transaction.update(userRef, "following", following)
                transaction.update(targetRef, "followers", FieldValue.increment(-1))
            } else {
                following.add(targetUserId)
                transaction.update(userRef, "following", following)
                transaction.update(targetRef, "followers", FieldValue.increment(1))
            }

            !isFollowing
        }.addOnSuccessListener { isNowFollowing ->
            onResult(isNowFollowing)
        }
    }

    fun isFollowing(targetUserId: String, onResult: (Boolean) -> Unit) {
        val userId = currentUserId
        db.collection("users").document(userId).get()
            .addOnSuccessListener { doc ->
                @Suppress("UNCHECKED_CAST")
                val following = doc.get("following") as? List<String> ?: emptyList()
                onResult(targetUserId in following)
            }
    }
}