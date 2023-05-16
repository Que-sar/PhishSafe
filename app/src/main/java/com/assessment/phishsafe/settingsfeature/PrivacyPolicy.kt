package com.assessment.phishsafe.settingsfeature

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.assessment.phishsafe.R
import com.assessment.phishsafe.databinding.ActivityPrivacyPolicyBinding
import com.assessment.phishsafe.homepagefeature.HomePageView

class PrivacyPolicy : AppCompatActivity() {

    private lateinit var binding: ActivityPrivacyPolicyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyPolicyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ThemeManager.isDarkThemeEnabled(this)){
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color_dark))
        }
        else {
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color))
        }

        binding.textViewPrivacyPolicy.setText(Html.fromHtml(getString(R.string.privacy_policy_long_text)))

        binding.navigateSettingsButton.setOnClickListener {
            navigateSettingsPage()
        }

    }

    private fun navigateSettingsPage() {
        val intent = Intent(this, SettingsPageView::class.java)
        startActivity(intent)
        finish()
    }
}