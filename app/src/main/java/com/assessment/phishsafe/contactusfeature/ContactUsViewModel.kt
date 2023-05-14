package com.assessment.phishsafe.contactusfeature

import android.content.Context
import android.telephony.PhoneNumberUtils.isGlobalPhoneNumber
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class ContactUsViewModel : ViewModel(){

    private lateinit var contactUsModel: ContactUsModel
    private val _uploadStatus = MutableLiveData<Boolean>()
    val uploadStatus: LiveData<Boolean>
        get() = _uploadStatus


    fun initContext(context: Context) {
        contactUsModel = ContactUsModel(context)
    }

    fun saveOnPause(email: String, phoneNumber: String, message: String){
        contactUsModel.saveEmail(email)
        contactUsModel.savePhoneNumber(phoneNumber)
        contactUsModel.saveMessage(message)
    }

    fun fetchMailOnResume(): String {
        return contactUsModel.getEmail()
    }

    fun fetchPhoneNumberOnResume(): String {
        return contactUsModel.getPhoneNumber()
    }

    fun fetchMessageOnResume(): String {
        return contactUsModel.getMessage()
    }


    private fun uniqueIdGenerator(): String {
        return UUID.randomUUID().toString()
    }

    private fun messageLengthChecker(text: String): Boolean{
        if (text.length < 201){
            return true
        }
        return false
    }
    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        if (phoneNumber.isEmpty()){
            return true
        }
        return isGlobalPhoneNumber(phoneNumber)
    }

    private fun isValidEmail(email: String): Boolean {
            return email.matches("[A-Za-z\\d+_.-]+@[A-Za-z\\d.-]+\$".toRegex())
    }


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