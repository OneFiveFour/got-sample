package io.redandroid.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.redandroid.data.database.GameOfThronesDatabase
import io.redandroid.data.database.HouseDao
import io.redandroid.data.database.HousePagingKeyDao
import javax.inject.Singleton

/**
 * This Hilt module provides all database related objects.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) : GameOfThronesDatabase {
        return Room
            .databaseBuilder(context, GameOfThronesDatabase::class.java, "got.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideHouseDao(database: GameOfThronesDatabase): HouseDao {
        return database.houseDao()
    }

    @Provides
    @Singleton
    fun provideHousePagingKeyDao(database: GameOfThronesDatabase): HousePagingKeyDao {
        return database.housePagingKeyDao()
    }

}