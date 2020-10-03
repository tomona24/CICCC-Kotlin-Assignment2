package com.example.assignment2_contacts

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_contacts.contactList.ContactListAdapter
import com.example.assignment2_contacts.contactList.ContactListViewModel
import com.example.assignment2_contacts.database.Contact
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var contactListViewModel : ContactListViewModel
    private val newContactActivityRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ContactListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        contactListViewModel = ViewModelProvider(this).get(ContactListViewModel::class.java)
        contactListViewModel.allContacts.observe(this, Observer { contacts ->
            contacts?.let { adapter.setContacts(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent = Intent(this@MainActivity, NewContactActivity::class.java)
            startActivityForResult(intent, newContactActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        lateinit var contactName : String
        lateinit var contactPhone : String
        if (requestCode == newContactActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewContactActivity.EXTRA_REPLY1)?.let {
                contactName = it
            }

        if (requestCode == newContactActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewContactActivity.EXTRA_REPLY2)?.let {
                contactPhone = it
            }
            if (contactName != null && contactPhone != null) {
                println(contactName)
                println(contactPhone)
                val contact = Contact(name = contactName, phoneNumber = contactPhone)
                contactListViewModel.insert(contact)
            }
        }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}