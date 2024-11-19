package com.example.practicecompose.repository


import com.example.practicecompose.data.Accounts
import com.example.practicecompose.data.Order
import com.example.practicecompose.data.Orders
import com.example.practicecompose.data.Users
import com.example.practicecompose.data.room.entity.NotesEntity
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class RepositoryImpl(
    private val apiService: APIService,
    private val postgrest: Postgrest,
    private val supabaseClient: SupabaseClient
) : RepositoryInterface {
    override suspend fun getData(): Response<List<Users>> = apiService.getData()

    override suspend fun getOrder(): Response<List<Orders>> = apiService.getOrder()

    override suspend fun getSupabaseOrder(): List<Order> = postgrest.from("Orders").select().decodeList<Order>()

    override suspend fun getSupabaseAccount(): List<Accounts> = postgrest.from("Users").select().decodeList<Accounts>()

    override suspend fun getSupabaseAuth() = supabaseClient.auth

}
