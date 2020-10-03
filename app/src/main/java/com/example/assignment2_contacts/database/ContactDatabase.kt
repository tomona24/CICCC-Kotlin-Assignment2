package com.example.assignment2_contacts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Contact::class), version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract val contactDatabaseDao: ContactDatabaseDao

    private class ContactDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var contactDao = database.contactDatabaseDao

                    var contact = Contact( name = "Tomona Sako", phoneNumber = "080-1809-3802")
                    contactDao.insert(contact)
                    contact =  Contact( name = "Tomona Sako2", phoneNumber = "080-1809-3802")
                    contactDao.insert(contact)
                    contact = Contact( name = "Tomona Sako3", phoneNumber = "080-1809-3802")
                    contactDao.insert(contact)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getInstance(context: Context,
        scope: CoroutineScope): ContactDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contact_database"
                    ).addCallback(ContactDatabaseCallback(scope)).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}