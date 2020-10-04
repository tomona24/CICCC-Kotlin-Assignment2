package com.example.assignment2_contacts.contactList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_contacts.R
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.databinding.ContactItemViewBinding

class ContactListAdapter internal constructor(val clickListener: ContactListener) : RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {
    private var contacts = listOf<Contact>()

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = contacts[position]
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.from(parent)
    }


    class ContactViewHolder private constructor(val binding: ContactItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Contact,
            clickListener: ContactListener
        ) {
            binding.contact = item
            binding.nameTextView.text = item.name
            binding.phoneNumTextView.text = item.phoneNumber
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ContactViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ContactItemViewBinding
                    .inflate(layoutInflater, parent, false)
                return ContactViewHolder(binding)
            }
        }

    }
    internal fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}

class ContactListener(val clickListener: (contactId: String) -> Unit) {
    fun onClick(contact: Contact) = clickListener(contact.name)

}