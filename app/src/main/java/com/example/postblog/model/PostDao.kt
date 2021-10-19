package com.example.postblog.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPost(post: Post)

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    suspend fun addListOfPost(listOfPost: ArrayList<Post>)

    @Query("SELECT * FROM post_table ORDER BY Id ASC")
    fun readAllPost(): LiveData<List<Post>>
}