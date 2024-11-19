package com.example.practicecompose.di



import com.example.practicecompose.BuildConfig.SUPABASE_ANON_KEY
import com.example.practicecompose.BuildConfig.SUPABASE_URL
import com.example.practicecompose.repository.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient

import io.github.jan.supabase.postgrest.Postgrest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    val url = SUPABASE_URL
    val apiKey = SUPABASE_ANON_KEY


    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = url,
            supabaseKey = apiKey
        ) {
            install(Postgrest)
            install(Auth)
//            install(GoTrue)
            /*install(ComposeAuth) {
                nativeGoogleLogin("WEB_GOOGLE_CLIENT_ID") //Use the Web Client ID, not the Android one!
            }*/

        }

    }

    @Provides
    @Singleton
    fun providePostgrest(client: SupabaseClient): Postgrest {
        return client.pluginManager.getPlugin(Postgrest)
    }

    private const val BASE_URL = "https://raw.githubusercontent.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)
}
