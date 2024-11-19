package com.example.practicecompose.repository

import com.example.practicecompose.data.Accounts
import com.example.practicecompose.data.Order
import com.example.practicecompose.data.Orders
import com.example.practicecompose.data.Users
import com.example.practicecompose.data.room.entity.NotesEntity
import io.github.jan.supabase.auth.Auth
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RepositoryInterface {

    suspend fun getData(): Response<List<Users>>

    suspend fun getOrder(): Response<List<Orders>>

    //Supabase
    suspend fun getSupabaseOrder(): List<Order>
    suspend fun getSupabaseAccount(): List<Accounts>
    suspend fun getSupabaseAuth(): Auth

}
