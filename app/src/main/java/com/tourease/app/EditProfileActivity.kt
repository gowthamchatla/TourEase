package com.tourease.app

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class EditProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private var selectedProfileUri: Uri? = null
    private var selectedCoverUri: Uri? = null
    private val PICK_PROFILE = 1001
    private val PICK_COVER = 1002

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val tvSave: TextView = findViewById(R.id.tvSave)
        val ivProfilePic: ImageView = findViewById(R.id.ivProfilePic)
        val ivCoverPhoto: ImageView = findViewById(R.id.ivCoverPhoto)
        val tvChangePhoto: TextView = findViewById(R.id.tvChangePhoto)
        val tvChangeCover: TextView = findViewById(R.id.tvChangeCover)
        val etUsername: EditText = findViewById(R.id.etUsername)
        val etBio: EditText = findViewById(R.id.etBio)
        val tvBioCount: TextView = findViewById(R.id.tvBioCount)
        val btnSave: Button = findViewById(R.id.btnSave)

        ivBack.setOnClickListener { finish() }

        // Bio counter
        etBio.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tvBioCount.text = "${s?.length ?: 0}/150"
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Load existing data
        loadCurrentProfile(etUsername, etBio, ivProfilePic, ivCoverPhoto)

        // Pick profile image
        ivProfilePic.setOnClickListener { pickImage(PICK_PROFILE) }
        tvChangePhoto.setOnClickListener { pickImage(PICK_PROFILE) }

        // Pick cover image
        ivCoverPhoto.setOnClickListener { pickImage(PICK_COVER) }
        tvChangeCover.setOnClickListener { pickImage(PICK_COVER) }

        // Save
        val saveAction = {
            val username = etUsername.text.toString().trim()
            val bio = etBio.text.toString().trim()
            if (username.isEmpty()) {
                Toast.makeText(this, "Username can't be empty", Toast.LENGTH_SHORT).show()
            } else {
                saveProfile(username, bio)
            }
        }

        tvSave.setOnClickListener { saveAction() }
        btnSave.setOnClickListener { saveAction() }
    }

    private fun pickImage(requestCode: Int) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, requestCode)
    }

    private fun loadCurrentProfile(etUsername: EditText, etBio: EditText, ivProfilePic: ImageView, ivCoverPhoto: ImageView) {
        val userId = auth.currentUser?.uid ?: return

        firestore.collection("users").document(userId)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.exists() && !isDestroyed && !isFinishing) {
                    etUsername.setText(doc.getString("username") ?: "")
                    etBio.setText(doc.getString("bio") ?: "")

                    val photoUrl = doc.getString("photoUrl")
                    if (!photoUrl.isNullOrEmpty()) {
                        Glide.with(this)
                            .load(photoUrl)
                            .placeholder(android.R.drawable.sym_def_app_icon)
                            .circleCrop()
                            .into(ivProfilePic)
                    }

                    val coverUrl = doc.getString("coverUrl")
                    if (!coverUrl.isNullOrEmpty()) {
                        Glide.with(this)
                            .load(coverUrl)
                            .centerCrop()
                            .into(ivCoverPhoto)
                    }
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri = data?.data ?: return
            when (requestCode) {
                PICK_PROFILE -> {
                    selectedProfileUri = uri
                    Glide.with(this).load(uri).circleCrop().into(findViewById(R.id.ivProfilePic))
                }
                PICK_COVER -> {
                    selectedCoverUri = uri
                    Glide.with(this).load(uri).centerCrop().into(findViewById(R.id.ivCoverPhoto))
                }
            }
        }
    }

    private fun saveProfile(username: String, bio: String) {
        val userId = auth.currentUser?.uid ?: return
        val btnSave: Button = findViewById(R.id.btnSave)
        btnSave.isEnabled = false
        btnSave.text = "Saving..."

        val updates = mutableMapOf<String, Any>(
            "username" to username,
            "bio" to bio
        )

        // Upload profile pic if changed
        uploadImage(selectedProfileUri, "profile_pictures/$userId.jpg") { profileUrl ->
            if (profileUrl != null) updates["photoUrl"] = profileUrl

            // Upload cover if changed
            uploadImage(selectedCoverUri, "cover_photos/$userId.jpg") { coverUrl ->
                if (coverUrl != null) updates["coverUrl"] = coverUrl

                // Save to Firestore
                firestore.collection("users").document(userId)
                    .update(updates)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Profile updated!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        firestore.collection("users").document(userId)
                            .set(updates)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Profile created!", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                                btnSave.isEnabled = true
                                btnSave.text = "Save Changes"
                            }
                    }
            }
        }
    }

    private fun uploadImage(uri: Uri?, path: String, onComplete: (String?) -> Unit) {
        if (uri == null) {
            onComplete(null)
            return
        }

        val ref = storage.reference.child(path)
        ref.putFile(uri)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { downloadUrl ->
                    onComplete(downloadUrl.toString())
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Upload failed: ${e.message}", Toast.LENGTH_SHORT).show()
                onComplete(null)
            }
    }
}