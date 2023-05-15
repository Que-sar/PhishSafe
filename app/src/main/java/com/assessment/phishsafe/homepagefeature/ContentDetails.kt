package com.assessment.phishsafe.homepagefeature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assessment.phishsafe.databinding.ActivityContentDetailsBinding
import com.assessment.phishsafe.settingsfeature.SettingsPageView

class ContentDetails : AppCompatActivity() {
    private lateinit var binding: ActivityContentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")

        binding.contentTitle.text = title
        binding.contentDescription.text = description

        binding.homePageNavButton.setOnClickListener {
            navigateHomePage()
        }


    }

    private fun navigateHomePage() {
        //val intent = Intent(this, HomePageView::class.java)
        //startActivity(intent)
        finish()
    }
}
