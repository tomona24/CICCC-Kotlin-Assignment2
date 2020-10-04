package com.example.assignment2_contacts

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.database.ContactDatabase
import com.example.assignment2_contacts.database.ContactDatabaseDao
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class ContactDatabaseTest {

    private lateinit var contactDao: ContactDatabaseDao
    private lateinit var db: ContactDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, ContactDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        contactDao = db.contactDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val contact = Contact(name = "ためし", phoneNumber = "090")
        contactDao.insert(contact)
        val contact2 = Contact(name = "ためし2", phoneNumber = "090")
        contactDao.insert(contact2)
        val contact3 = Contact(name = "ため3", phoneNumber = "090")
        contactDao.insert(contact3)
//        val tonight = sleepDao.getTonight()
//        Assert.assertEquals(tonight?.sleepQuality, -1)
    }
}