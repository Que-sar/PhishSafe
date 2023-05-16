package com.assessment.phishsafe.contactusfeature

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
// The model file of the contact us feature, handling the shared preferences saving
// and database insertion
// Takes in a context to handle the upload of data(from the ContactUsView file
class ContactUsModel(private val context: Context) {

    private lateinit var database : DatabaseReference

    /* This declares the shared preference for the email, phone and message data.
    * Enables the user to come back to the typed in data as they were before exiting,
    * turning screen etc.
    * The following 3 methods put the data into shared preferences. */
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("my_sf", Context.MODE_PRIVATE)

    fun saveEmail(email: String){
        val editor = sharedPreferences.edit()
        editor.putString("email_data", email)
        editor.apply()
    }

    fun savePhoneNumber(phoneNumber: String){
        val editor = sharedPreferences.edit()
        editor.putString("phone_data", phoneNumber)
        editor.apply()
    }

    fun saveMessage(message: String){
        val editor = sharedPreferences.edit()
        editor.putString("message_data", message)
        editor.apply()
    }

    // The following 3 functions get the shared preferences that have been saved previously

    fun getEmail(): String {
        return sharedPreferences.getString("email_data", "") ?: ""
    }

    fun getPhoneNumber(): String {
        return sharedPreferences.getString("phone_data", "") ?: ""
    }

    fun getMessage(): String {
        return sharedPreferences.getString("message_data", "") ?: ""
    }

    // Uploads the data to the firebase database, taking in the data class object and uses a
    // mutable live data to display if upload has been successful for the view later on
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
