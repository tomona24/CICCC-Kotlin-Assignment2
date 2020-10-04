package com.example.assignment2_contacts.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(contact: Contact)

    @Query("SELECT * from contact_table ORDER BY name ASC")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("DELETE FROM contact_table")
    fun deleteAll()

}