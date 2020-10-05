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
import com.example.assignment2_contacts.databinding.ContactItemSectionViewBinding
import com.example.assignment2_contacts.databinding.ContactItemViewBinding
import java.lang.ClassCastException

class ContactListAdapter internal constructor(val clickListener: ContactListener) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(ContactDiffCallback()) {
    private var contacts = listOf<Contact>()
    private val SECTION_TYPE = 0
    private val CONTACT_TYPE = 1

    override fun getItemCount(): Int = contacts.size

    override fun getItemViewType(position: Int): Int {
        val item = contacts[position]
        if(item.name.length == 1 && item.phoneNumber == "") {
            return SECTION_TYPE
        }
            return CONTACT_TYPE
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        if (viewType == CONTACT_TYPE) {
            return ContactViewHolder.from(parent)
        } else if (viewType == SECTION_TYPE) {
            return SectionViewHolder.from(parent)
        } else {
            throw ClassCastException("Unknown viewType ${viewType}")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ContactViewHolder -> {
                val item = contacts[position]
                holder.bind(item, clickListener)
            }

            is SectionViewHolder -> {
                val item = contacts[position]
                holder.bind(item)
            }
        }
        }


    class SectionViewHolder private constructor(val binding: ContactItemSectionViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: Contact
        ) {
            binding.sectionTitleView.text = item.name
        }

        companion object {
            fun from(parent: ViewGroup): SectionViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ContactItemSectionViewBinding
                    .inflate(layoutInflater, parent, false)
                return SectionViewHolder(binding)
            }
        }

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
        var contactsForRecyclerView = arrayListOf<Contact>()
        var currentInitial = 64
        for (item in contacts) {
            if (currentInitial.toChar().toString()  != item.initial) {
                while (currentInitial  < item.initial.single().toInt() && currentInitial < 91) {
                    currentInitial += 1
                    var section: Contact
                    if (currentInitial == 91) {
                        section = Contact(name = "#")
                    } else {
                        section = Contact(name = currentInitial.toChar().toString())
                    }
                    contactsForRecyclerView.add(section)

                }
                contactsForRecyclerView.add(item)
            } else {
                contactsForRecyclerView.add(item)
            }
        }
        this.contacts = contactsForRecyclerView.toList()
        notifyDataSetChanged()
    }
}

class ContactListener(val clickListener: (contactId: String) -> Unit) {
    fun onClick(contact: Contact) = clickListener(contact.name)

}

class ContactDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem === newItem
    }
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.name == newItem.name
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
