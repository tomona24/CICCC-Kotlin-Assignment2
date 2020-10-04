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

    private val _navigateToContactList = MutableLiveData<Boolean?>()
    val navigateToContactList: LiveData<Boolean?>
        get() = _navigateToContactList


    fun doneNavigating() {
        _navigateToContactList.value = null
    }

//    fun onSetNewContact(name: String = "ためし", phoneNum: String ="試し") {
    fun onSetNewContact() {
        val name = "ためし"
        val phoneNum = "ためしNum"

        viewModelScope.launch{
            val contact = Contact(name = name, phoneNumber = phoneNum)
            database.insert(contact)

            _navigateToContactList.value = true
        }

    }
}