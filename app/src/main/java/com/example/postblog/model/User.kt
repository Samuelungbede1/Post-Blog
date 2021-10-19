package com.example.postblog.model

import androidx.room.Entity


@Entity
data class User(
    var id: Int,
    var name: String,
    var username: String,
    var email: String
)
