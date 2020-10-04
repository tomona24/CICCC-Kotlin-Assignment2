package com.example.assignment2_contacts.contactDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.assignment2_contacts.R
import com.example.assignment2_contacts.database.ContactDatabase
import com.example.assignment2_contacts.databinding.FragmentContactDetailBinding

class ContactDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentContactDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_contact_detail, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = ContactDetailFragmentArgs.fromBundle(arguments)

        // Create an instance of the ViewModel Factory.
        val dataSource = ContactDatabase.getInstance(application).contactDatabaseDao
        val viewModelFactory = SleepDetailViewModelFactory(arguments.contactNameKey, dataSource)

        // Get a reference to the ViewModel associated with this fragment.
        val contactDetailViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ContactDetailViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.contactDetailViewModel = contactDetailViewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }
}