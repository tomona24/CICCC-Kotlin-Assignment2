package com.example.assignment2_contacts.network


data class Result (
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