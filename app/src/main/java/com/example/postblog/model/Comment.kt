package com.example.postblog.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "comment_table")
@Parcelize
data class Comment(
    @PrimaryKey
    var id: Int,
    var name :String,
    var body: String
): Parcelable
