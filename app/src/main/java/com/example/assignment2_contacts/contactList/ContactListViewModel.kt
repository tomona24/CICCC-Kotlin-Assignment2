package com.example.assignment2_contacts.contactList

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.database.ContactDatabase
import com.example.assignment2_contacts.database.ContactDatabaseDao
import com.example.assignment2_contacts.network.NetworkRandomUserContainer
import com.example.assignment2_contacts.network.RandomUserApi
import com.example.assignment2_contacts.network.RandomUserProperty
import com.example.assignment2_contacts.network.asDatabaseModel
import com.example.assignment2_contacts.repository.ContactRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ContactListViewModel(application: Application,
    dataSource: ContactDatabaseDao) : ViewModel() {
    private val contactRepository = ContactRepository(ContactDatabase.getInstance(application))

    val database = dataSource
//    val allContacts =  database.getAllContacts()
    val allContacts = contactRepository.contacts


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

    private val _properties = MutableLiveData<NetworkRandomUserContainer>()

    val properties: LiveData<NetworkRandomUserContainer>
        get() = _properties

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    /**
     * Flag to display the error message. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    init {
        refreshDataFromRepository()
//        getRandomUserProperties()
    }

    private fun getRandomUserProperties() {
        viewModelScope.launch {
            try {
                val results = RandomUserApi.retrofitService.getProperties()
                _properties.value = results
                println("成功")
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                println("失敗")
                println(e.message)

            }
        }
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
        try {
            contactRepository.refreshContacts()
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false

        } catch (networkError: IOException) {
            if (allContacts.value.isNullOrEmpty())
            _eventNetworkError.value = true
        }
    }
    }
}