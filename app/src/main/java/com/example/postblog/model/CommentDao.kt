package com.example.postblog.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addComment(comment: Comment)

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListOfComment(listOfComment: ArrayList<Comment>)

    @Query("SELECT * FROM comment_table ORDER BY Id ASC")
    fun readAllComment(): LiveData<List<Comment>>
}