package com.factumex.prueba.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    private var urlApiPoket = "https://pokeapi.co/api/v2/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }
        .readTimeout(360, TimeUnit.SECONDS)
        .connectTimeout(360, TimeUnit.SECONDS)
        .build()



    @RetrofitPokemon
    @Provides
    @Singleton
    fun providesPokemonRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(urlApiPoket)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

}