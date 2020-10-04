package com.example.assignment2_contacts.contactList

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.assignment2_contacts.database.Contact


@BindingAdapter("setContactNameString")
fun TextView.setContactNameString(item: Contact?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("setContactGenderString")
fun TextView.setContactGenderString(item: Contact?) {
    item?.let {
        text = "female"
//        text = item.gender
    }
}

@BindingAdapter("setContactPhoneNumberString")
fun TextView.setContactPhoneNumberString(item: Contact?) {
    item?.let {
        text = item.phoneNumber
    }
}

@BindingAdapter("setContactEmailString")
fun TextView.setContactEmailString(item: Contact?) {
    item?.let {
        text = "example@example.com"
//        text = item.email
    }
}

@BindingAdapter("setContactAddressString")
fun TextView.setContactAddressString(item: Contact?) {
    item?.let {
        text = "Vancouver, Canada"
//        text = item.address
    }
}