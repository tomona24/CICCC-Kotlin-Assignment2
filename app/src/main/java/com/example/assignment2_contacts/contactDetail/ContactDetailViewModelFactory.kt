package com.example.assignment2_contacts.contactDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment2_contacts.database.ContactDatabaseDao

class SleepDetailViewModelFactory(
    private val contactNameKey: String,
    private val dataSource: ContactDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactDetailViewModel::class.java)) {
            return ContactDetailViewModel(contactNameKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}