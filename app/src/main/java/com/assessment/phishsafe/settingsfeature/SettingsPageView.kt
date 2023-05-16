package com.assessment.phishsafe.settingsfeature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.assessment.phishsafe.R
import com.assessment.phishsafe.databinding.ActivitySettingsPageViewBinding
import com.assessment.phishsafe.homepagefeature.HomePageView

// Settings page, setting the background of the app using shared preferences and displays the
// privacy policy
class SettingsPageView : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsPageViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Settings")

        // Setting the dark background using the shared preferences inside Theme Manager
        val isDarkThemeEnabled = ThemeManager.isDarkThemeEnabled(this)
        binding.themeChangeSwitch.isChecked = isDarkThemeEnabled

        binding.themeChangeSwitch.setOnCheckedChangeListener { _, isChecked ->
            ThemeManager.setDarkThemeEnabled(this, isChecked)
            changeTheme()
        }

        // button for navigating to privacy policy or back to home
        binding.buttonPrivacyPolicy.setOnClickListener {
            val intent = Intent(this, PrivacyPolicy::class.java)
            startActivity(intent)
        }
        binding.navigateBackButton.setOnClickListener {
            navigateHomePage()
        }
        // calls change theme to change if needed
        changeTheme()
    }

    // Based on shared preference value, changes the background color
    fun changeTheme(){

        if (ThemeManager.isDarkThemeEnabled(this)){
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color_dark))
        }
        else {
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color))
        }
    }

    // Home page navigator
    private fun navigateHomePage() {
        val intent = Intent(this, HomePageView::class.java)
        startActivity(intent)
        finish()
    }
}