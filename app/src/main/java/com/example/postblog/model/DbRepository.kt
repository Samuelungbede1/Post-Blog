package com.example.postblog.model

import androidx.lifecycle.LiveData
import com.example.postblog.Network.RetrofitInstance

class DbRepository(private val postDao: PostDao, private val commentDao: CommentDao) {

    val readAllPostData : LiveData<List<Post>> = postDao.readAllPost()
    val readAllCommentData : LiveData<List<Comment>> = commentDao.readAllComment()

    suspend fun addPost (post:Post){
        postDao.addPost(post)
    }

    suspend fun addListOfPost (listOfPost: ArrayList<Post>){
        postDao.addListOfPost(listOfPost)
    }


    suspend fun addComment (comment: Comment){
        commentDao.addComment(comment)
    }

    suspend fun addListOComment (listOfComment: ArrayList<Comment>){
        commentDao.addListOfComment(listOfComment)
    }


}