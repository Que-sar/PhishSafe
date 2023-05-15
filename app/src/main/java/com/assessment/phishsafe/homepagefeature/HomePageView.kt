package com.assessment.phishsafe.homepagefeature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.assessment.phishsafe.R
import com.assessment.phishsafe.contactusfeature.ContactUsView
import com.assessment.phishsafe.databinding.ActivityHomePageViewBinding
import com.assessment.phishsafe.settingsfeature.SettingsPageView

class HomePageView : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageViewBinding
    private var listTitles = mutableListOf<String>()
    private var listDescriptions = mutableListOf<String>()
    private var listImages = mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contentAddToList()

        binding.settingsNavButton.setOnClickListener {
            navigateSettingsPage()
        }

        binding.contactUsNavButton.setOnClickListener {
            Toast.makeText(this,"Let us know about your opinions on the app!", Toast.LENGTH_LONG).show()
            navigateContactUsPage()
        }

        binding.contentList.layoutManager = LinearLayoutManager(this)
        binding.contentList.adapter = RecyclerAdapter(listTitles, listDescriptions, listImages)


    }


    private fun contentToList(title: String, description: String, image: Int){
        listTitles.add(title)
        listDescriptions.add(description)
        listImages.add(image)
    }

    private fun contentAddToList(){
        for (i in 1..10){
            contentToList("Title ${i}", "Description ${i}", R.mipmap.ic_launcher_round)
        }
    }


    private fun navigateSettingsPage(){
        val intent = Intent(this, SettingsPageView::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateContactUsPage(){
        val intent = Intent(this, ContactUsView::class.java)
        startActivity(intent)
        finish()
    }


}