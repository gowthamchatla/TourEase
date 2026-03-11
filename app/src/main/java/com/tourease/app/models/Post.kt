package com.tourease.app.models

import com.google.firebase.Timestamp

data class Post(
    var id: String = "",
    var userId: String = "",
    var userName: String = "",
    var userPhotoBase64: String = "",
    var text: String = "",
    var imageUrl: String = "",
    var location: String = "",
    var vibeTags: List<String> = emptyList(),
    var isAnonymous: Boolean = false,
    var fireCount: Int = 0,
    var firedBy: List<String> = emptyList(),
    var commentCount: Int = 0,
    var bookmarkedBy: List<String> = emptyList(),
    var timestamp: Timestamp = Timestamp.now()
) {
    // No-arg constructor for Firestore
    constructor() : this("", "", "", "", "", "", "", emptyList(), false, 0, emptyList(), 0, emptyList(), Timestamp.now())
}

data class Comment(
    var id: String = "",
    var postId: String = "",
    var userId: String = "",
    var userName: String = "",
    var text: String = "",
    var timestamp: Timestamp = Timestamp.now()
) {
    constructor() : this("", "", "", "", "", Timestamp.now())
}