package com.example.assignment2_contacts.contactList

import com.example.assignment2_contacts.database.ContactDatabaseDao
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ContactListViewModelFactory (
    private val dataSource: ContactDatabaseDao,
    private val application: Application
): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((ContactListViewModel::class.java))) {
            return ContactListViewModel(application, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}