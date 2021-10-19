package com.example.postblog.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (private val repository: Repository): ViewModel() {
    val postResponse : MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val commentResponse : MutableLiveData<Response<List<Comment>>> = MutableLiveData()

    var tempPostList : MutableLiveData<Response<List<Post>>> = postResponse




    fun getPost(){
        viewModelScope.launch {
            var response = repository.getPosts()
            postResponse.value = response
        }
    }


    fun getPostComments(postId: Int){
        viewModelScope.launch {
            var response = repository.getIndividualUserPostComment(postId)
            commentResponse.value = response
        }
    }

    fun search (searchTerm: String){
        viewModelScope.launch {
            var response = repository.searchPost(searchTerm)
            tempPostList.value = response
        }
    }

}