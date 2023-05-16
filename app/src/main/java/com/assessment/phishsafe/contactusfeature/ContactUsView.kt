package com.assessment.phishsafe.contactusfeature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.assessment.phishsafe.R
import com.assessment.phishsafe.databinding.ActivityContactUsViewBinding
import com.assessment.phishsafe.homepagefeature.HomePageView

// View file for the Contact us feature
class ContactUsView : AppCompatActivity() {

    private lateinit var binding : ActivityContactUsViewBinding
    private lateinit var viewModel: ContactUsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Contact Us")

        viewModel = ViewModelProvider(this)[ContactUsViewModel::class.java]
        viewModel.initContext(this)


        // sets background to dark if the setting is enabled through getting the shared preference
        // inside the setting feature
        if (ThemeManager.isDarkThemeEnabled(this)){
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color_dark))
        }
        else {
            binding.root.setBackgroundColor(ContextCompat.getColor(this, R.color.background_color))
        }

        // observes the live data to see if the insertion was a success and clears the fields again
        viewModel.uploadStatus.observe(this) { success ->
            if (success) {
                // Data upload successful, clear the fields
                clearInputFields()
            } else {
                // Data upload failed, show an error message
                Toast.makeText(this,"Please check your network connection and try again.", Toast.LENGTH_LONG).show()
            }
        }

        // navigate to home page with the back button
        binding.homePageNavButton.setOnClickListener{
            navigateToHomePage()
        }

        // When data submitted, fills the data class object which will be then validated and sent
        // to firebase database if found correct
        binding.contactButton.setOnClickListener {
            val submittedData = ContactUsData(
                binding.contactEmailField.text.toString(),
                binding.contactPhoneField.text.toString(),
                binding.contactMessageText.text.toString()
            )
            viewModel.contactButtonWasClicked(this, submittedData)

        }

        }

    // Saves the filled in information when the activity gets to the next first lifecycle phase
    // it saves it into a shared preference
    override fun onPause() {
        super.onPause()
        saveAll()
    }

    // When resumed, sets the fields back to what they were before, if null, makes them empty.
    override fun onResume() {
        super.onResume()

        binding.contactEmailField.setText(viewModel.fetchMailOnResume())
        binding.contactPhoneField.setText(viewModel.fetchPhoneNumberOnResume())
        binding.contactMessageText.setText(viewModel.fetchMessageOnResume())


    }

    // The function which saves it to shared preferences using the instance of the viewmodel file
    // as it handles the communication between model and view
    private fun saveAll() {

        val email = binding.contactEmailField.text.toString()
        val phoneNumber = binding.contactPhoneField.text.toString()
        val message = binding.contactMessageText.text.toString()

        viewModel.saveOnPause(email, phoneNumber, message)
    }

    // Saves upon navigation as well.
    private fun navigateToHomePage() {
        saveAll()
        val intent = Intent(this, HomePageView::class.java)
        startActivity(intent)
        finish()
    }

    // method which clears the fields.
    private fun clearInputFields() {
        binding.contactEmailField.setText("")
        binding.contactPhoneField.setText("")
        binding.contactMessageText.setText("")
    }

}