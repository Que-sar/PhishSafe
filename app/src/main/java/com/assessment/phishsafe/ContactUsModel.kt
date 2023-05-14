package com.assessment.phishsafe

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.assessment.phishsafe.ContactUsData

class ContactUsModel(private val context: Context) {

    private lateinit var database : DatabaseReference
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_sf", Context.MODE_PRIVATE)

    fun saveEmail(email: String){
        val editor = sharedPreferences.edit()
        editor.putString("email_data", email.toString())
        editor.apply()
    }

    fun savePhoneNumber(phoneNumber: String){
        val editor = sharedPreferences.edit()
        editor.putString("phone_data", phoneNumber.toString())
        editor.apply()
    }

    fun saveMessage(message: String){
        val editor = sharedPreferences.edit()
        editor.putString("message_data", message.toString())
        editor.apply()
    }


    fun getEmail(): String {
        return sharedPreferences.getString("email_data", "") ?: ""
    }

    fun getPhoneNumber(): String {
        return sharedPreferences.getString("phone_data", "") ?: ""
    }

    fun getMessage(): String {
        return sharedPreferences.getString("message_data", "") ?: ""
    }

    fun uploadContactDetails(id: String, contactData: ContactUsData, uploadStatus: MutableLiveData<Boolean>)
    {

        database = FirebaseDatabase.getInstance("https://phishsafe-9253b-default-rtdb.europe-west1.firebasedatabase.app").getReference("ContactDetails")
        database.child(id).setValue(contactData).addOnSuccessListener{
                Toast.makeText(context, "We have received your message!", Toast.LENGTH_LONG).show()
            uploadStatus.value = true
                }.addOnFailureListener{
                    Toast.makeText(context, "Please check your network connection and try again.", Toast.LENGTH_LONG).show()
            uploadStatus.value = false
        }
    }
}
