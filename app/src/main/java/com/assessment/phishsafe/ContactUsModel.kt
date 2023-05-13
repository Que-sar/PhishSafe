package com.assessment.phishsafe

import android.content.Context
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ContactUsModel {

    private lateinit var database : DatabaseReference

    fun uploadContactDetails(context: Context, id: String, contactData: ContactData)
    {

        database = FirebaseDatabase.getInstance("https://phishsafe-9253b-default-rtdb.europe-west1.firebasedatabase.app").getReference("ContactDetails")
        database.child(id).setValue(contactData).addOnSuccessListener{
                Toast.makeText(context, "We have received your message!", Toast.LENGTH_LONG).show()
                }.addOnFailureListener{
                    Toast.makeText(context, "Please check your network connection and try again.", Toast.LENGTH_LONG).show()
        }
    }
}
