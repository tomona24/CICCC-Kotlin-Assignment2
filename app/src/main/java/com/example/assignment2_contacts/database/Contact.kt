package com.example.assignment2_contacts.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact (
    @PrimaryKey
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "phone_number")
    var phoneNumber: String

) {
    var initial = name.substring(0,1)
}



//@ColumnInfo(name = "email")
//var email: String = "example@com",
//
//@ColumnInfo(name = "gender")
//var gender: String = "female",
//
//@ColumnInfo(name = "address")
//var address: String = "xxx xxxx xxxxxxxxxx"