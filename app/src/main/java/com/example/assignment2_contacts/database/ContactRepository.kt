package com.example.assignment2_contacts.database

import androidx.lifecycle.LiveData

class ContactRepository(private val contactDBDao: ContactDatabaseDao) {
    val allContacts: LiveData<List<Contact>> = contactDBDao.getAllContacts()

    suspend fun insert(contact: Contact){
        contactDBDao.insert(contact)
    }
}