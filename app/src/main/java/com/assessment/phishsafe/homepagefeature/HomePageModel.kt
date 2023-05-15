package com.assessment.phishsafe.homepagefeature

import com.google.firebase.database.*

class HomePageModel {

    private lateinit var database : DatabaseReference

    fun fetchContentFromDB(callback: (List<AwarenessContent>) -> Unit) {
        database =
            FirebaseDatabase.getInstance("https://phishsafe-9253b-default-rtdb.europe-west1.firebasedatabase.app")
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
                TODO("Not yet implemented")
            }
        }
        )
    }



}