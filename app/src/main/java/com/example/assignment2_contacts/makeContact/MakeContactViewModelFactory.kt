package com.example.assignment2_contacts.makeContact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment2_contacts.database.ContactDatabaseDao
import java.lang.IllegalArgumentException

class MakeContactViewModelFactory(
    private val dataSource: ContactDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if(modelClass.isAssignableFrom(MakeContactViewModel::class.java)) {
        return MakeContactViewModel(dataSource) as T
    }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}