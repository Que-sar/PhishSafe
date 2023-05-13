package com.assessment.phishsafe

class ContactUsViewModel {

    // Viewmodel stuff
    // Unique id using val uniqueId = UUID.randomUUID().toString()
    // 200 character handle for text field
    // calling functions for model
    // saving it if not sent using sharedpref
    // put all into a package named ContactUsFeature
    // only accept numbers in phone number
    // basic checks for email
    // check if all required is filled out

    data class ContactData(
        val phoneNumber: String,
        val email: String,
        val text: String
    )
}