package com.tourease.app

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SearchUsersActivity : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var rvResults: RecyclerView
    private var allUsers = mutableListOf<Map<String, Any>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_users)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val etSearch: EditText = findViewById(R.id.etSearch)
        rvResults = findViewById(R.id.rvSearchResults)

        rvResults.layoutManager = LinearLayoutManager(this)

        ivBack.setOnClickListener { finish() }

        // Load all users once
        loadAllUsers()

        // Search filter
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterUsers(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun loadAllUsers() {
        val currentUserId = auth.currentUser?.uid ?: ""

        firestore.collection("users")
            .get()
            .addOnSuccessListener { snapshot ->
                allUsers.clear()
                for (doc in snapshot.documents) {
                    // Skip current user
                    if (doc.id == currentUserId) continue

                    val userData = mutableMapOf<String, Any>(
                        "userId" to doc.id,
                        "username" to (doc.getString("username") ?: "User"),
                        "bio" to (doc.getString("bio") ?: ""),
                        "photoUrl" to (doc.getString("photoUrl") ?: "")
                    )
                    allUsers.add(userData)
                }

                // Show all users initially
                showUsers(allUsers)
            }
    }

    private fun filterUsers(query: String) {
        if (query.isEmpty()) {
            showUsers(allUsers)
            return
        }

        val filtered = allUsers.filter { user ->
            val username = (user["username"] as? String) ?: ""
            username.lowercase().contains(query.lowercase())
        }
        showUsers(filtered)
    }

    private fun showUsers(users: List<Map<String, Any>>) {
        rvResults.adapter = SearchUsersAdapter(this, users) { userId ->
            // Open their profile
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
    }
}