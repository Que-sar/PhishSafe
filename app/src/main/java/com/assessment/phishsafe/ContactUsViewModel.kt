package com.assessment.phishsafe

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import java.util.*

class ContactUsViewModel{

    fun uniqueIdGenerator(): String {
        return UUID.randomUUID().toString()
    }

    fun messageLengthChecker(text: String): Boolean{
        if (text.length < 201){
            return true
        }
        return false
    }
    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val pattern = Regex("""^\+[1-9]\d{1,14}$""")
        return pattern.matches(phoneNumber)
    }

    fun isValidEmail(email: String): Boolean {
        val pattern = Regex("""^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}${'$'}""")
        return pattern.matches(email)
    }

    fun requiredPresent(context: Context, email: String, text: String) {
        if (email.isEmpty()){
            Toast.makeText(context, "Please fill in your email field.", Toast.LENGTH_LONG).show()
        }
        if (text.isEmpty()){
            Toast.makeText(context, "Please fill in your message field.", Toast.LENGTH_LONG).show()
        }
    }

    // calling functions for model
    // saving it if not sent using sharedpref

    // put all into a package named ContactUsFeature


}