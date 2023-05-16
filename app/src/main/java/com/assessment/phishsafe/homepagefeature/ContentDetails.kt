package com.assessment.phishsafe.homepagefeature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.assessment.phishsafe.R
import com.assessment.phishsafe.databinding.ActivityContentDetailsBinding

// Activity, which provides the content(Title and Description) of the topic at hand
class ContentDetails : AppCompatActivity() {
    private lateinit var binding: ActivityContentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // hides action bar
        getSupportActionBar()?.hide()

        // Checks if background should be night mode
        if (ThemeManager.isDarkThemeEnabled(this)){
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color_dark))
        }
        else {
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color))
        }

        // Gets the strings passed down from the put extra inside the view file, handling the UI
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")


        binding.contentTitle.text = title
        binding.contentDescription.text = description

        // navigate button for back the home page(by going back using finish)
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
