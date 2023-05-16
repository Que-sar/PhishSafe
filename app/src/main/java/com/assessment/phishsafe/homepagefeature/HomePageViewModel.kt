package com.assessment.phishsafe.homepagefeature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// Viewmodel file, passing the data from the view to the model, uses livedata for it.
class HomePageViewModel: ViewModel() {
    private val repository = HomePageModel()
    val contentData: MutableLiveData<List<AwarenessContent>> = MutableLiveData()

    fun fetchContentFromDB() {
        repository.fetchContentFromDB { contentList ->
            contentData.value = contentList
        }
    }

}