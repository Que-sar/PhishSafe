package com.assessment.phishsafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.assessment.phishsafe.databinding.ActivityContactUsViewBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ContactUsView : AppCompatActivity() {

    private lateinit var binding : ActivityContactUsViewBinding
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactUsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.contactButton.setOnClickListener {

                val name = "data1"
                val exampleData = "I am the first data to be uploaded"

                database = FirebaseDatabase.getInstance("https://phishsafe-9253b-default-rtdb.europe-west1.firebasedatabase.app").getReference("ContactDetails")
                database.child(name).setValue(exampleData).addOnSuccessListener {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                }

        }


    }
}