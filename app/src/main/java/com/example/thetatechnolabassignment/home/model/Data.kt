package com.example.thetatechnotest.home.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class Data(
    val avatar: String,
    val email: String,
    val first_name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val last_name: String
)