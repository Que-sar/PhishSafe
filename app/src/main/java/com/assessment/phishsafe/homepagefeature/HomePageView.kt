package com.assessment.phishsafe.homepagefeature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.phishsafe.R
import com.assessment.phishsafe.contactusfeature.ContactUsView
import com.assessment.phishsafe.databinding.ActivityHomePageViewBinding
import com.assessment.phishsafe.settingsfeature.SettingsPageView

class HomePageView : AppCompatActivity() {


    private lateinit var binding: ActivityHomePageViewBinding
    private val viewModel: HomePageViewModel by lazy {
        ViewModelProvider(this)[HomePageViewModel::class.java]
    }

    private lateinit var adapter: RecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Home")


        //sets background based on shared preferences
        if (ThemeManager.isDarkThemeEnabled(this)){
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color_dark))
        }
        else {
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color))
        }

        //navigation buttons to different features
        binding.settingsNavButton.setOnClickListener {
            navigateSettingsPage()
        }

        binding.contactUsNavButton.setOnClickListener {
            Toast.makeText(this, "Let us know about your opinions on the app!", Toast.LENGTH_LONG)
                .show()
            navigateContactUsPage()
        }

        // uses recycler adapter and observes a live data variable which then is used for change
        // without this, the contentList does not refresh and the recycler view does not load anything
        // as it had no knowledge before fetching that there is anything changed
        // Then it fetches from the database and does it again.
        // ContentData is a livedata instance in the viewmodel file of the feature, designed to pass
        // the data further to the model
        adapter = RecyclerAdapter(emptyList()){ contentItem ->
            navigateToContentDetails(contentItem)
        }
        binding.contentList.layoutManager = LinearLayoutManager(this)
        binding.contentList.adapter = adapter

        viewModel.contentData.observe(this) { contentList ->
            adapter.setContentList(contentList)
        }

        viewModel.fetchContentFromDB()
    }

    // Called when the listing is clicked on, passes the fetched title and description down to the
    // ContentDetails file
    private fun navigateToContentDetails(contentItem: AwarenessContent) {
        val intent = Intent(this, ContentDetails::class.java)
        intent.putExtra("title", contentItem.title)
        intent.putExtra("description", contentItem.description)
        startActivity(intent)
    }

    // Navigator buttons, to the about us page and the settings page.
    private fun navigateSettingsPage() {
        val intent = Intent(this, SettingsPageView::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateContactUsPage() {
        val intent = Intent(this, ContactUsView::class.java)
        startActivity(intent)
        finish()
    }



}
