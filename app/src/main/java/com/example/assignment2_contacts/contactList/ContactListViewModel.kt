package com.example.assignment2_contacts.contactList

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.database.ContactDatabaseDao
import com.example.assignment2_contacts.network.RandomUserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactListViewModel(application: Application,
    dataSource: ContactDatabaseDao) : ViewModel() {
    val database = dataSource
    val allContacts =  database.getAllContacts()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    private suspend fun insert(contact: Contact) {
        database.insert(contact)
    }

    private suspend fun deleteAll() {
        database.deleteAll()
    }

    /**
     * If this is non-null, immediately navigate to [SleepQualityFragment] and call [doneNavigating]
     */
    private val _navigateToMakeContact = MutableLiveData<Contact>()
    val navigateToMakeContact: LiveData<Contact>
        get() = _navigateToMakeContact


    private val _navigateToContactDetail = MutableLiveData<String>()
    val navigateToContactDetail: LiveData<String>
        get() = _navigateToContactDetail

    fun onContactClicked(nameKey: String) {
        _navigateToContactDetail.value = nameKey
    }

    fun onContactDetailNavigated() {
        _navigateToContactDetail.value = null
    }


    // network
    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getRandomUserProperties()
    }

    private fun getRandomUserProperties() {
        RandomUserApi.retrofitService.getProperties().enqueue(
            object: Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                    println("失敗")
                    println(t.message)

                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                    println("成功")
                    println(response.body())
                }
            })

    }


}