package com.example.postblog.model

import com.example.postblog.Network.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getUsers(): Response<List<User>> {
        return RetrofitInstance.api.getUsers()
    }


    suspend fun getPosts(): Response<List<Post>> {
        return RetrofitInstance.api.getPosts()
    }

    suspend fun getIndividualUserPostComment(postId: Int) : Response<List<Comment>> {
        return RetrofitInstance.api.getIndividualUserPostComment(postId)
    }


    suspend fun addComment (comment: Comment) : Response<Comment> {
        return RetrofitInstance.api.addComment(comment)
    }

    suspend fun addPost (post: Post) : Response<Post> {
        return RetrofitInstance.api.addPost(post)
    }

    suspend fun searchPost(searchTerm: String) : Response<List<Post>> {
        return RetrofitInstance.api.searchForPost(searchTerm)
    }

}