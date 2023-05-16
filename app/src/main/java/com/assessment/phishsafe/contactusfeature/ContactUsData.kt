package com.assessment.phishsafe.contactusfeature

/*
* A data class to provide an object with assignable contacts
*/

data class ContactUsData(
    val email: String = "",
    val phoneNumber: String = "",
    val messageField: String = ""
)
