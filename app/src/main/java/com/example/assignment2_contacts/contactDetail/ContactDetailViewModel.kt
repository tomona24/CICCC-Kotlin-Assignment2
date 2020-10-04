package com.example.assignment2_contacts.contactDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.database.ContactDatabaseDao

class ContactDetailViewModel (
        private val contactNameKey: String = "",
        dataSource: ContactDatabaseDao) : ViewModel() {

        /**
         * Hold a reference to SleepDatabase via its SleepDatabaseDao.
         */
        val database = dataSource

        private val contact: LiveData<Contact>
        fun getContact() = contact


        init {
            contact = database.getContactWithId(contactNameKey)
        }


    }