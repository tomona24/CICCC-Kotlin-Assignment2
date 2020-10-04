package com.example.assignment2_contacts.contactList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.assignment2_contacts.R
import com.example.assignment2_contacts.database.ContactDatabase
import com.example.assignment2_contacts.databinding.FragmentContactListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ContactListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentContactListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_contact_list, container, false)

        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = ContactDatabase.getInstance(application).contactDatabaseDao
        val viewModelFactory = ContactListViewModelFactory(dataSource, application)

        val contactListViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ContactListViewModel::class.java)

        binding.contactListViewModel = contactListViewModel

        binding.setLifecycleOwner(this)  // ここ

        val adapter = ContactListAdapter()
        binding.recyclerview.adapter = adapter

        contactListViewModel.allContacts.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                adapter.setContacts(it)
            }

        })

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<FloatingActionButton>(R.id.fab_fragment).setOnClickListener {
//            findNavController().navigate(R.id.action_contactList_to_makeContact) }
        }
    }