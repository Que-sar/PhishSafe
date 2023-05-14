package com.assessment.phishsafe.contactusfeature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.assessment.phishsafe.databinding.ActivityContactUsViewBinding
import com.assessment.phishsafe.homepagefeature.HomePageView

class ContactUsView : AppCompatActivity() {

    private lateinit var binding : ActivityContactUsViewBinding
    private lateinit var viewModel: ContactUsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ContactUsViewModel::class.java]
        viewModel.initContext(this)


        viewModel.uploadStatus.observe(this) { success ->
            if (success) {
                // Data upload successful, clear the fields
                clearInputFields()
            } else {
                // Data upload failed, show an error message
                Toast.makeText(this,"Please check your network connection and try again.", Toast.LENGTH_LONG).show()
            }
        }

        binding.homePageNavButton.setOnClickListener{
            navigateToHomePage()
        }

        binding.contactButton.setOnClickListener {
            val submittedData = ContactUsData(
                binding.contactEmailField.text.toString(),
                binding.contactPhoneField.text.toString(),
                binding.contactMessageText.text.toString()
            )
            viewModel.contactButtonWasClicked(this, submittedData)

        }

        }

    override fun onPause() {
        super.onPause()
        saveAll()
    }

    override fun onResume() {
        super.onResume()

        binding.contactEmailField.setText(viewModel.fetchMailOnResume())
        binding.contactPhoneField.setText(viewModel.fetchPhoneNumberOnResume())
        binding.contactMessageText.setText(viewModel.fetchMessageOnResume())


    }

    private fun saveAll() {

        val email = binding.contactEmailField.text.toString()
        val phoneNumber = binding.contactPhoneField.text.toString()
        val message = binding.contactMessageText.text.toString()

        viewModel.saveOnPause(email, phoneNumber, message)
    }

    private fun navigateToHomePage() {
        saveAll()
        val intent = Intent(this, HomePageView::class.java)
        startActivity(intent)
        finish()
    }

    private fun clearInputFields() {
        binding.contactEmailField.setText("")
        binding.contactPhoneField.setText("")
        binding.contactMessageText.setText("")
    }

}