package com.assessment.phishsafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assessment.phishsafe.databinding.ActivityContactUsViewBinding

class ContactUsView : AppCompatActivity() {

    private lateinit var binding : ActivityContactUsViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.contactButton.setOnClickListener {


                }

        }


}