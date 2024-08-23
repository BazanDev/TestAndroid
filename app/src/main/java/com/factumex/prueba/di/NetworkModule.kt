package com.factumex.prueba.di

import com.factumex.prueba.data.datasource.remote.api.ApiServicePokemon
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providePokemonClient(@RetrofitPokemon retrofit: Retrofit): ApiServicePokemon {
        return retrofit.create(ApiServicePokemon::class.java)

    }

}