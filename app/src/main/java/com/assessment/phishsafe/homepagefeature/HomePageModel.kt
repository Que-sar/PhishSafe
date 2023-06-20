package com.assessment.phishsafe.homepagefeature

import android.util.Log
import com.google.firebase.database.*

// File handling the server side for the home page's content providing feature, which fetches
// The title and description of the content from the database and displays them accordingly
class HomePageModel {

    private lateinit var database : DatabaseReference

    // Fetches data and returns a callback of the data object type which will be passed down.
    // Containing all titles and descriptions as a list of data objects
    fun fetchContentFromDB(callback: (List<AwarenessContent>) -> Unit) {
        database =
            FirebaseDatabase.getInstance("firebasedatabase.app")
                .getReference("AwarenessContent")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val contentList = mutableListOf<AwarenessContent>()
                for (subData in dataSnapshot.children) {

                    val title = subData.child("title").getValue(String::class.java).toString()
                    val description = subData.child("Description").getValue(String::class.java).toString()
                    val contentItem = AwarenessContent(title, description)
                    contentList.add(contentItem)

                }
                callback(contentList)

            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Warning", "There has been an issue getting the content.")
            }
        }
        )
    }



}
