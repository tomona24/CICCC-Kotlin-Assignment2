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

class ContactListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var contacts = emptyList<Contact>()

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contactNameItemView: TextView = itemView.findViewById(R.id.name_text_view)
        val contactPhoneNumItemView: TextView = itemView.findViewById(R.id.phone_num_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = inflater.inflate(R.layout.contact_item_view, parent, false)
        return ContactViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = contacts[position]
        holder.contactNameItemView.text = current.name
        holder.contactPhoneNumItemView.text = current.phoneNumber
    }

    internal fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = contacts.size



}