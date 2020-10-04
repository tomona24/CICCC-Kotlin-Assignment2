package com.example.assignment2_contacts.contactList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_contacts.R
import com.example.assignment2_contacts.database.Contact
import com.example.assignment2_contacts.databinding.ContactItemViewBinding

class ContactListAdapter internal constructor(val clickListener: ContactListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(ContactDiffCallback()) {
//    RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {
    private var contacts = listOf<Contact>()

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ContactViewHolder -> {
//                val item = getItem(position)!! as DataItem.ContactItem
//                holder.bind(item.contact, clickListener)
                val item = contacts[position]
                holder.bind(item, clickListener)
            }
        }
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

class ContactDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.name == newItem.name
    }
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

sealed class DataItem {
    abstract val name: String
    data class ContactItem(val contact: Contact): DataItem(){
        override val name: String = contact.name
    }
    object Header: DataItem() {
        override val name: String = ""

    }
}
