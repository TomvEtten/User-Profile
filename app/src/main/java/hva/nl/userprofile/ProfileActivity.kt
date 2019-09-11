package hva.nl.userprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_profile.*
import kotlinx.android.synthetic.main.activity_profile.*

const val PROFILE_EXTRA = "PROFILE_EXTRA"

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews()
    }

    private fun initViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "This is your profile!"

        val profile = intent.getParcelableExtra<Profile>(PROFILE_EXTRA) ?: return
        tvName.text = getString(R.string.name , profile.firstName, profile.lastName)
        tvDesciption.text = profile.description
        if (profile.ImageUri == null) {
            return
        }
        ivProfilePicture.setImageURI(profile.ImageUri)
    }
}
