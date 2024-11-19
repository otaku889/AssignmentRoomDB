package com.example.practicecompose.repository

import com.example.practicecompose.data.Orders
import com.example.practicecompose.data.Users
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("/otaku889/albums/refs/heads/main/users")
    suspend fun getData(): Response<List<Users>>

    @GET("/otaku889/albums/refs/heads/main/orders")
    suspend fun getOrder(): Response<List<Orders>>


}
