package com.example.assignment2_contacts.contactList

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_contacts.R
import com.example.assignment2_contacts.database.Contact
import kotlinx.android.synthetic.main.contact_item_view.view.*
import java.util.Date.from

class ContactListAdapter internal constructor() : RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {

    private var contacts = emptyList<Contact>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = contacts[position]
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
//        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
//        val itemView = inflater.inflate(R.layout.contact_item_view, parent, false)
//        return ContactViewHolder(itemView)
        return ContactViewHolder.from(parent)
    }

    class ContactViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactNameItemView: TextView = itemView.findViewById(R.id.name_text_view)
        val contactPhoneNumItemView: TextView = itemView.findViewById(R.id.phone_num_text_view)

        fun bind(item: Contact) {
            val res = itemView.context.resources
            contactPhoneNumItemView.text = item.name
            contactPhoneNumItemView.text = item.phoneNumber
        }

        companion object {
            fun from(parent: ViewGroup): ContactViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.contact_item_view, parent, false)

                return ContactViewHolder(view)
            }
        }
    }





    internal fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }




}