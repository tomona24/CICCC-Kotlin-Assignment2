package com.example.assignment2_contacts.contactList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.database.ContactDatabase
import com.example.assignment2_contacts.database.ContactDatabaseDao
import com.example.assignment2_contacts.database.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ContactRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allContacts: LiveData<List<Contact>>

    init {
        val contactsDao = ContactDatabase.getInstance(application, viewModelScope).contactDatabaseDao
        repository = ContactRepository(contactsDao)
        allContacts = repository.allContacts
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(contact)
    }
}