package com.example.postblog.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response


class DbViewModel(application: Application) : AndroidViewModel(application) {

    val postResponse : MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val commentResponse : MutableLiveData<Response<List<Comment>>> = MutableLiveData()


     val readAllPost : LiveData<List<Post>>
     val readAllComment : LiveData<List<Comment>>
    private val repository: DbRepository

    init {
        val postDao= AppDataBase.getDataBase(application).postDao()
        val commentDao= AppDataBase.getDataBase(application).commentDao()
        repository = DbRepository(postDao,commentDao)
        readAllPost = repository.readAllPostData
        readAllComment = repository.readAllCommentData
    }


    fun addPost(post: Post){
        viewModelScope.launch {
            repository.addPost(post)
        }
    }

    fun addListOfPost(listOfPost: ArrayList<Post>){
        viewModelScope.launch {
            repository.addListOfPost(listOfPost)
        }
    }


    fun addListOfComment(listOfComment: ArrayList<Comment>){
        viewModelScope.launch {
            repository.addListOComment(listOfComment)
        }
    }

}