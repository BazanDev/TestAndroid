package com.factumex.prueba.di

import android.content.Context
import androidx.room.Room
import com.factumex.prueba.data.datasource.local.CreateDataBase
import com.factumex.prueba.data.datasource.local.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): CreateDataBase {
        return Room.databaseBuilder(
            context,
            CreateDataBase::class.java, "pokemon_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePokemonDao(createDataBase: CreateDataBase): PokemonDao {
        return createDataBase.pokemonDao()
    }
}