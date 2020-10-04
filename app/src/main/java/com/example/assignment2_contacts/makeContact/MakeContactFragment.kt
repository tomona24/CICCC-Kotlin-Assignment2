package com.example.assignment2_contacts.makeContact

import com.example.assignment2_contacts.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.database.ContactDatabase
import com.example.assignment2_contacts.databinding.FragmentMakeContactBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MakeContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment and binding
        val binding: FragmentMakeContactBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_make_contact, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = ContactDatabase.getInstance(application).contactDatabaseDao
        val viewModelFactory = MakeContactViewModelFactory(dataSource)

        val makeContactViewModel = ViewModelProvider(this, viewModelFactory).get(MakeContactViewModel::class.java)

        binding.makeContactViewModel = makeContactViewModel

        makeContactViewModel.navigateToContactList.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    R.id.action_makeContact_to_contactList)
                makeContactViewModel.doneNavigating()
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_cancel).setOnClickListener {
            findNavController().navigate(R.id.action_makeContact_to_contactList)
        }

//        view.findViewById<Button>(R.id.button_save).setOnClickListener {
//            findNavController().navigate(R.id.action_makeContact_to_contactList)
//        }
    }
}