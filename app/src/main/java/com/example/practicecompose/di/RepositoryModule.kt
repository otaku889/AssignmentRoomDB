package com.example.practicecompose.di

import com.example.practicecompose.repository.APIService
import com.example.practicecompose.repository.RepositoryImpl
import com.example.practicecompose.repository.RepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesRepository(apiService: APIService, postgrest: Postgrest, supabaseClient: SupabaseClient): RepositoryInterface = RepositoryImpl(apiService,postgrest,supabaseClient)

}