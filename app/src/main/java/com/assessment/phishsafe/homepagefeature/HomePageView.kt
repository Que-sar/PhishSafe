package com.assessment.phishsafe.homepagefeature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.assessment.phishsafe.contactusfeature.ContactUsView
import com.assessment.phishsafe.databinding.ActivityHomePageViewBinding
import com.assessment.phishsafe.settingsfeature.SettingsPageView

class HomePageView : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settingsNavButton.setOnClickListener {
            navigateSettingsPage()
        }

        binding.contactUsNavButton.setOnClickListener {
            Toast.makeText(this,"Let us know about your opinions on the app!", Toast.LENGTH_LONG).show()
            navigateContactUsPage()
        }

    }


    private fun navigateSettingsPage(){
        val intent = Intent(this, SettingsPageView::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateContactUsPage(){
        val intent = Intent(this, ContactUsView::class.java)
        startActivity(intent)
        finish()
    }


}