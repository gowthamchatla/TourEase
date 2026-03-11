package com.tourease.app

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.tourease.app.repository.SocialRepository
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreatePostActivity : AppCompatActivity() {

    private val GALLERY_REQUEST = 1001
    private val CAMERA_REQUEST = 1002
    private val CAMERA_PERMISSION = 2001

    private val repository = SocialRepository()

    private var selectedImageUri: Uri? = null
    private var cameraPhotoPath: String? = null
    private val selectedTags = mutableListOf<String>()

    private lateinit var etPostText: EditText
    private lateinit var etLocation: EditText
    private lateinit var cardImagePreview: CardView
    private lateinit var ivImagePreview: ImageView
    private lateinit var switchAnonymous: SwitchCompat
    private lateinit var btnPost: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        supportActionBar?.hide()

        etPostText = findViewById(R.id.etPostText)
        etLocation = findViewById(R.id.etLocation)
        cardImagePreview = findViewById(R.id.cardImagePreview)
        ivImagePreview = findViewById(R.id.ivImagePreview)
        switchAnonymous = findViewById(R.id.switchAnonymous)
        btnPost = findViewById(R.id.btnPost)

        val tvCancel: TextView = findViewById(R.id.tvCancel)
        val ivCamera: ImageView = findViewById(R.id.ivCamera)
        val ivGallery: ImageView = findViewById(R.id.ivGallery)
        val ivRemoveImage: ImageView = findViewById(R.id.ivRemoveImage)

        tvCancel.setOnClickListener { finish() }

        ivGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, GALLERY_REQUEST)
        }

        ivCamera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION)
            } else {
                openCamera()
            }
        }

        ivRemoveImage.setOnClickListener {
            selectedImageUri = null
            cardImagePreview.visibility = View.GONE
        }

        // Vibe tag setup
        setupVibeTags()

        // Post button
        btnPost.setOnClickListener { createPost() }
    }

    private fun setupVibeTags() {
        val tagMap = mapOf(
            R.id.tagHotTake to "🔥 Hot Take",
            R.id.tagOverrated to "💀 Overrated",
            R.id.tagHiddenGem to "💎 Hidden Gem",
            R.id.tagTouristTrap to "⚠️ Tourist Trap",
            R.id.tagFoodie to "🍜 Foodie Find",
            R.id.tagBudget to "💸 Budget Hack",
            R.id.tagVibes to "🌅 Vibes Only",
            R.id.tagSolo to "🎒 Solo Trip"
        )

        for ((viewId, tagText) in tagMap) {
            val tagView = findViewById<TextView>(viewId)
            tagView.setOnClickListener {
                if (tagText in selectedTags) {
                    selectedTags.remove(tagText)
                    tagView.setBackgroundResource(R.drawable.bg_vibe_tag)
                    tagView.setTextColor(android.graphics.Color.parseColor("#CCCCDD"))
                } else {
                    if (selectedTags.size >= 3) {
                        Toast.makeText(this, "Max 3 vibes per post", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    selectedTags.add(tagText)
                    tagView.setBackgroundResource(R.drawable.bg_tab_selected)
                    tagView.setTextColor(android.graphics.Color.parseColor("#B388FF"))
                }
            }
        }
    }

    private fun openCamera() {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFile = File.createTempFile("IMG_${timeStamp}_", ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES))
        cameraPhotoPath = imageFile.absolutePath

        val photoUri = FileProvider.getUriForFile(this, "${packageName}.fileprovider", imageFile)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        startActivityForResult(intent, CAMERA_REQUEST)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST -> {
                    selectedImageUri = data?.data
                    if (selectedImageUri != null) {
                        cardImagePreview.visibility = View.VISIBLE
                        ivImagePreview.setImageURI(selectedImageUri)
                    }
                }
                CAMERA_REQUEST -> {
                    cameraPhotoPath?.let { path ->
                        val file = File(path)
                        selectedImageUri = Uri.fromFile(file)
                        cardImagePreview.visibility = View.VISIBLE
                        ivImagePreview.setImageURI(selectedImageUri)
                    }
                }
            }
        }
    }

    private fun createPost() {
        val text = etPostText.text.toString().trim()
        val location = etLocation.text.toString().trim()
        val isAnonymous = switchAnonymous.isChecked

        if (text.isEmpty()) {
            Toast.makeText(this, "Write something first!", Toast.LENGTH_SHORT).show()
            return
        }

        btnPost.isEnabled = false
        btnPost.text = "Posting..."

        repository.createPost(
            text = text,
            imageUri = selectedImageUri,
            location = location,
            vibeTags = selectedTags.toList(),
            isAnonymous = isAnonymous,
            onSuccess = {
                runOnUiThread {
                    Toast.makeText(this, "Posted! 🔥", Toast.LENGTH_SHORT).show()
                    finish()
                }
            },
            onFailure = { error ->
                runOnUiThread {
                    btnPost.isEnabled = true
                    btnPost.text = "Post"
                    Toast.makeText(this, "Failed: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}