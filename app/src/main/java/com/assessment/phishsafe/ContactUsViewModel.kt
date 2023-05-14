package com.assessment.phishsafe

import android.content.Context
import android.widget.Toast
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
        if (phoneNumber.isEmpty()){
            return true
        }
        val pattern = Regex("""^\+[1-9]\d{1,14}$""")
        return pattern.matches(phoneNumber)
    }

    fun isValidEmail(email: String): Boolean {
        val pattern = Regex("""^[A-Za-z\d._%+-]+@[A-Za-z\d.-]+\.[A-Za-z]{2,}${'$'}""")
        return pattern.matches(email)
    }

    fun contactButtonWasClicked(context: Context, data: ContactUsData): Unit{
        val messageLength = messageLengthChecker(data.messageField)
        val phoneValidity = isValidPhoneNumber(data.phoneNumber)
        val emailValidity = isValidEmail(data.email)

        if (!messageLength){
            return Toast.makeText(context, "Please fill in your message field.", Toast.LENGTH_LONG).show()
        }

        if (!phoneValidity){
            return Toast.makeText(context, "Please fill in your phone field.", Toast.LENGTH_LONG).show()
        }

        if (!emailValidity){
            return Toast.makeText(context, "Please fill in your email field.", Toast.LENGTH_LONG).show()
        }

        val uniqueID = uniqueIdGenerator()




    }


    // put all into a package named ContactUsFeature


}