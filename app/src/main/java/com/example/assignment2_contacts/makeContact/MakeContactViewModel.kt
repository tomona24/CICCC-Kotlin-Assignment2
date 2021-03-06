package com.example.assignment2_contacts.makeContact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.database.ContactDatabaseDao
import kotlinx.coroutines.launch

class MakeContactViewModel (dataSource: ContactDatabaseDao): ViewModel() {

    val database = dataSource

    var nameText : String = ""
    var phoneText : String = ""

    private val _navigateToContactList = MutableLiveData<Boolean?>()
    val navigateToContactList: LiveData<Boolean?>
        get() = _navigateToContactList


    fun doneNavigating() {
        _navigateToContactList.value = null
    }

    fun onSetNewContact() {
        viewModelScope.launch {
            if (validateName() && validatePhone()) {
                val name = nameText
                val phone = phoneText
                val contact = Contact(name = name, phoneNumber = phone)
                database.insert(contact)
                _navigateToContactList.value = true
            }
        }
    }

    fun validateName (): Boolean {
        return nameText.isNotEmpty() && nameText.split(" ").size == 2
    }

    fun validatePhone (): Boolean {
        return phoneText.isNotEmpty() && phoneText.length == 10
    }

}