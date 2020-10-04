package com.example.assignment2_contacts

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_contacts.contactList.ContactListAdapter
import com.example.assignment2_contacts.contactList.ContactListViewModel
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.database.ContactDatabase
import com.example.assignment2_contacts.database.ContactDatabaseDao
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle item selection
//        return when (item.itemId) {
//            R.id.menu_refresh -> {
//
//                true
//            }
//            R.id.menu_clear -> {
//                showHelp()
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

}
