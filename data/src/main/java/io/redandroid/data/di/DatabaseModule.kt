package io.redandroid.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.redandroid.data.database.GameOfThronesDatabase
import javax.inject.Singleton

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

}