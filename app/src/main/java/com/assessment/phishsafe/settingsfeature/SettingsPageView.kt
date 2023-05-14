package com.assessment.phishsafe.settingsfeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assessment.phishsafe.databinding.ActivityHomePageViewBinding
import com.assessment.phishsafe.databinding.ActivitySettingsPageViewBinding

class SettingsPageView : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsPageViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsPageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}