package com.example.assignment2_contacts.contactList

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.database.ContactDatabaseDao
import com.example.assignment2_contacts.network.RandomUserApi
import com.example.assignment2_contacts.network.RandomUserProperty
import com.example.assignment2_contacts.network.Result
import kotlinx.coroutines.launch
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

    private val _properties = MutableLiveData<Result>()

    val properties: LiveData<Result>
        get() = _properties

    init {
        getRandomUserProperties()
    }

    private fun getRandomUserProperties() {
        viewModelScope.launch {
            try {
                val results = RandomUserApi.retrofitService.getProperties()
                _properties.value = results
                addContactsFromProperties(results.results)
                println("成功")
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                println("失敗")
                println(e.message)

            }
        }
    }

    private suspend fun addContactsFromProperties(results: List<RandomUserProperty>) {
        val list = mutableListOf<Contact>()
        val org = allContacts.value ?: listOf()
        list.addAll(org)
        println("ここにはきてる")
        println(results)
        for (x in 0..(results.size - org.size - 1)) {
            val item = results[x]
            var contact = Contact()
            contact.name = item.name.first + " " + item.name.last
            contact.phoneNumber = item.phone
            database.insert(contact)
        }

    }
}