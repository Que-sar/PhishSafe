package com.assessment.phishsafe.homepagefeature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.settingsNavButton.setOnClickListener {
            navigateSettingsPage()
        }

        binding.contactUsNavButton.setOnClickListener {
            Toast.makeText(this, "Let us know about your opinions on the app!", Toast.LENGTH_LONG)
                .show()
            navigateContactUsPage()
        }


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

    private fun navigateToContentDetails(contentItem: AwarenessContent) {
        val intent = Intent(this, ContentDetails::class.java)
        intent.putExtra("title", contentItem.title)
        intent.putExtra("description", contentItem.description)
        startActivity(intent)
    }

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
