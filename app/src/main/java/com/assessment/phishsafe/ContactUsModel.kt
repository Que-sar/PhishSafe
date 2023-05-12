package com.assessment.phishsafe

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ContactUsModel {

    private lateinit var database : DatabaseReference

    fun uploadContactDetails(id: String, phoneNumber: String, email:String, text: String)
    {
        database = FirebaseDatabase.getInstance("https://phishsafe-9253b-default-rtdb.europe-west1.firebasedatabase.app").getReference("ContactDetails")

    }
}