package hva.nl.userprofile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_profile.*

const val GALLERY_REQUEST_CODE = 100

class CreateProfileActivity : AppCompatActivity() {
    private var profileImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)
        initViews()
    }

    private fun initViews() {
        btnOpenPicGallery.setOnClickListener { openPicGallery() }
        btnProfileSubmit.setOnClickListener { submitProfile() }
    }

    private fun openPicGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK || requestCode != GALLERY_REQUEST_CODE) {
            return
        }
        profileImageUri = data?.data
        ivUserPictureEdit.setImageURI(profileImageUri)
    }

    private fun submitProfile() {
        val profile = Profile(
            tvFirstName.text.toString(),
            tvLastName.text.toString(),
            tvProfileDescription.text.toString(),
            profileImageUri
        )
        val profileActivityIntent = Intent(this, ProfileActivity::class.java)
        profileActivityIntent.putExtra(PROFILE_EXTRA, profile)
        startActivity(profileActivityIntent)
    }

}
