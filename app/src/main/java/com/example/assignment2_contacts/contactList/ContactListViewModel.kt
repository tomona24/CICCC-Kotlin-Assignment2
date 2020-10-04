package com.example.assignment2_contacts.contactList

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.database.ContactDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

}