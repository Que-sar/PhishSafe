package com.assessment.phishsafe.settingsfeature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.assessment.phishsafe.databinding.ActivitySettingsPageViewBinding
import com.assessment.phishsafe.homepagefeature.HomePageView

class SettingsPageView : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsPageViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Settings")

        binding.themeChangeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
        }


        binding.navigateBackButton.setOnClickListener {
            navigateHomePage()
        }

    }

    private fun navigateHomePage() {
        val intent = Intent(this, HomePageView::class.java)
        startActivity(intent)
        finish()
    }
}