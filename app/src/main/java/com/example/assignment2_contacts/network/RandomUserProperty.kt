package com.example.assignment2_contacts.network

import com.example.assignment2_contacts.database.Contact


data class NetworkRandomUserContainer (
    val results: List<RandomUserProperty>
)

data class RandomUserProperty(
    val name: UserName,
    val email: String,
    val phone: String,
    val location: Location,
    val gender: String
)

data class UserName (
    val title: String,
    val first: String,
    val last: String
)

data class Location (
    val street: Street,
    val city : String,
    val state: String,
    val postcode: String
)

data class Street(
    val number: String,
    val name: String
)


fun NetworkRandomUserContainer.asDatabaseModel(): List<Contact> {
    return results.map {
        Contact(
            name = it.name.first + " " + it.name.last,
            phoneNumber = it.phone
        )
    }
}