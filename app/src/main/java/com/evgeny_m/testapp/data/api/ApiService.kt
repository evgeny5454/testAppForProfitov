package com.evgeny_m.testapp.data.api

import com.evgeny_m.testapp.data.models.ListPosts
import com.evgeny_m.testapp.data.models.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/v1/hot")
    suspend fun getListPosts(): Response<ListPosts>

    @GET("api/v1/post/{post_id}")
    suspend fun getPost(@Path("post_id") post_id: Int): Response<Post>
}