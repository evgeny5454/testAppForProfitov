package com.evgeny_m.testapp.data.repository

import com.evgeny_m.testapp.data.api.RetrofitInstance
import com.evgeny_m.testapp.data.models.ListPosts
import com.evgeny_m.testapp.data.models.Post
import retrofit2.Response

class Repository {
    suspend fun getPosts(): Response<ListPosts> {
        return RetrofitInstance.api.getListPosts()
    }

    suspend fun getPost(postId: Int): Response<Post> {
        return RetrofitInstance.api.getPost(post_id = postId)
    }
}