package com.evgeny_m.testapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evgeny_m.testapp.data.models.ListPosts
import com.evgeny_m.testapp.data.models.Post
import com.evgeny_m.testapp.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val repository = Repository()
    private val mutableListPosts: MutableLiveData<Response<ListPosts>> = MutableLiveData()
    private val mutablePost : MutableLiveData<Response<Post>> = MutableLiveData()
    val post : LiveData<Response<Post>> = mutablePost

    fun getListPosts() : LiveData<Response<ListPosts>> {
        val listPosts : LiveData<Response<ListPosts>> = mutableListPosts
        viewModelScope.launch {
            mutableListPosts.value = repository.getPosts()
        }
        return listPosts
    }

    fun getPost(postId : Int)  {
        viewModelScope.launch {
            mutablePost.value = repository.getPost(postId)
        }
    }
}