package com.assessment.phishsafe.contactusfeature

import android.content.Context
import android.telephony.PhoneNumberUtils.isGlobalPhoneNumber
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


// ViewModel file of the contact us feature
// handles the logic between database(model) and view file
class ContactUsViewModel : ViewModel(){

    private lateinit var contactUsModel: ContactUsModel
    // mutable bool to display outcome of insertion
    private val _uploadStatus = MutableLiveData<Boolean>()
    val uploadStatus: LiveData<Boolean>
        get() = _uploadStatus


    // creates the context for the model file by passing it from the view
    fun initContext(context: Context) {
        contactUsModel = ContactUsModel(context)
    }

    // saves into shared preference using the methods of the model file
    fun saveOnPause(email: String, phoneNumber: String, message: String){
        contactUsModel.saveEmail(email)
        contactUsModel.savePhoneNumber(phoneNumber)
        contactUsModel.saveMessage(message)
    }

    // gets the data from the shared preferences
    fun fetchMailOnResume(): String {
        return contactUsModel.getEmail()
    }

    fun fetchPhoneNumberOnResume(): String {
        return contactUsModel.getPhoneNumber()
    }

    fun fetchMessageOnResume(): String {
        return contactUsModel.getMessage()
    }


    // generates unique ID for the insertion id
    private fun uniqueIdGenerator(): String {
        return UUID.randomUUID().toString()
    }

    // Check if message is more than 200 characters.
    private fun messageLengthChecker(text: String): Boolean{
        if (text.length < 201){
            return true
        }
        return false
    }

    // Check if phone number is valid, but since its not required, returns true if empty
    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        if (phoneNumber.isEmpty()){
            return true
        }
        return isGlobalPhoneNumber(phoneNumber)
    }

    // Using regular expressions, checks for validity of email.
    private fun isValidEmail(email: String): Boolean {
            return email.matches("[A-Za-z\\d+_.-]+@[A-Za-z\\d.-]+\$".toRegex())
    }


    // Main function, getting everything ready and checked out for the database insertion in the
    // model file. If passes, uses the methods above to create an entry and pass it to the model.
    fun contactButtonWasClicked(context: Context, data: ContactUsData){
        val messageLength = messageLengthChecker(data.messageField)
        val phoneValidity = isValidPhoneNumber(data.phoneNumber)
        val emailValidity = isValidEmail(data.email)

        if (!emailValidity){
            return Toast.makeText(context, "Please fill in your email field.", Toast.LENGTH_LONG).show()
        }

        if (!phoneValidity){
            return Toast.makeText(context, "Please fill in your phone field.", Toast.LENGTH_LONG).show()
        }

        if (!messageLength){
            return Toast.makeText(context, "Please fill in your message field.", Toast.LENGTH_LONG).show()
        }

        val uniqueID = uniqueIdGenerator()
        contactUsModel.uploadContactDetails(uniqueID, data, _uploadStatus)

    }
}