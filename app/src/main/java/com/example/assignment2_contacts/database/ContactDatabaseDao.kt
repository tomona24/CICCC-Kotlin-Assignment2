package com.example.assignment2_contacts.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(contact: Contact)

    @Query("SELECT * from contact_table ORDER BY name ASC")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("DELETE FROM contact_table")
    suspend fun deleteAll()

    @Query("SELECT * from contact_table WHERE name = :key")
    fun getContactWithId(key: String): LiveData<Contact>
}