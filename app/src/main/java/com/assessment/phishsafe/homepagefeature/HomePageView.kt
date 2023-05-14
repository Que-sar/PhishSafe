package com.assessment.phishsafe.homepagefeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assessment.phishsafe.databinding.ActivityHomePageViewBinding

class HomePageView : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}