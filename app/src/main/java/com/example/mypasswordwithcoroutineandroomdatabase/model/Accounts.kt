package com.example.mypasswordwithcoroutineandroomdatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Accounts")
data class Accounts (
    @ColumnInfo(name = "accountName")
    var accountName : String,

    @ColumnInfo(name = "userName")
    var userName : String,

    @ColumnInfo(name = "password")
    var password : String,

    @ColumnInfo(name = "comment")
    var comment: String

    ){

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}