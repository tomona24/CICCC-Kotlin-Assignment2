package com.example.assignment2_contacts.repository

import androidx.lifecycle.LiveData
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.database.ContactDatabase
import com.example.assignment2_contacts.network.RandomUserApi
import com.example.assignment2_contacts.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class ContactRepository (private val database: ContactDatabase) {
    val contacts: LiveData<List<Contact>> = database.contactDatabaseDao.getAllContacts()

    suspend fun refreshContacts() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh videos is called")
            val contactList = RandomUserApi.retrofitService.getProperties()
            database.contactDatabaseDao.insertAll(contactList.asDatabaseModel())

        }
    }
}