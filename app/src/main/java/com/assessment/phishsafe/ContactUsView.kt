package com.assessment.phishsafe

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assessment.phishsafe.databinding.ActivityContactUsViewBinding

class ContactUsView : AppCompatActivity() {

    private lateinit var binding : ActivityContactUsViewBinding
    private lateinit var sharedPreference: SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreference =  getSharedPreferences("shared_data", MODE_PRIVATE)
        editor = sharedPreference.edit()

        binding.contactButton.setOnClickListener {
            val submittedData = ContactUsData(
                binding.contactPhoneField.text.toString(),
                binding.contactEmailField.text.toString(),
                binding.contactMessageText.text.toString()
            )

        }

        }

    override fun onPause() {
        super.onPause()

        editor.apply {
            putString("email_data", binding.contactEmailField.toString())
            putString("phone_data", binding.contactPhoneField.toString())
            putString("message_data", binding.contactMessageText.toString())
            commit()
        }
    }

    override fun onResume() {
        super.onResume()

        binding.contactEmailField.setText(sharedPreference.getString("email_data", ""))
        binding.contactPhoneField.setText(sharedPreference.getString("phone_data", ""))
        binding.contactMessageText.setText(sharedPreference.getString("message_data", ""))
    }

}