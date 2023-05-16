package com.assessment.phishsafe.settingsfeature

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.assessment.phishsafe.R
import com.assessment.phishsafe.databinding.ActivityPrivacyPolicyBinding

// Contains the privacy policy template for an application(Its only an example, not real)
class PrivacyPolicy : AppCompatActivity() {

    private lateinit var binding: ActivityPrivacyPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Privacy Policy")

        // sets background based on shared preference
        if (ThemeManager.isDarkThemeEnabled(this)){
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color_dark))
        }
        else {
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color))
        }

        // Displays the privacy policy correctly via the html formatting, which came with the template
        binding.textViewPrivacyPolicy.setText(Html.fromHtml(getString(R.string.privacy_policy_long_text)))

        binding.navigateSettingsButton.setOnClickListener {
            navigateSettingsPage()
        }

    }

    // Settings page navigator
    private fun navigateSettingsPage() {
        val intent = Intent(this, SettingsPageView::class.java)
        startActivity(intent)
        finish()
    }
}