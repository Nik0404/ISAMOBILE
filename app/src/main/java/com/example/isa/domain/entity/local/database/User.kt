package com.example.isa.domain.entity.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val login: String,
    val name: String,
    val surname: String,
    val thirdName: String,
    val department: String,
    val email: String,
    val post: String,
    val phone: String,
//    val divisionId: Int
)

