package com.example.postblog.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "post_table")
@Parcelize
data class Post (
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
) : Parcelable
