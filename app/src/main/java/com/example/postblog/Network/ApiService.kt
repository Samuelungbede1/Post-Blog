package com.example.postblog.Network

import com.example.postblog.model.Comment
import com.example.postblog.model.Post
import com.example.postblog.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>


    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("posts")
    suspend fun searchForPost(@Query("posts") post: String): Response<List<Post>>


    @GET("comments")
    suspend fun getIndividualUserPostComment(@Query("postId") postId: Int): Response<List<Comment>>

    @POST("comments")
    suspend fun addComment(@Body comment: Comment): Response<Comment>

    @POST("posts")
    suspend fun addPost(@Body post: Post): Response<Post>
}