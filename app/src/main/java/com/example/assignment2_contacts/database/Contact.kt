package com.example.assignment2_contacts.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.assignment2_contacts.network.RandomUserProperty

@Entity(tableName = "contact_table")
data class Contact (
    @PrimaryKey
    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "phone_number")
    var phoneNumber: String = ""

) {
    var initial = name.substring(0,1)
}
