package io.redandroid.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.redandroid.data.database.GameOfThronesDatabase

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class TestDatabaseModule {

    /**
     * Provides an inMemoryDatabase for easy integration testing.
     */
    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context): GameOfThronesDatabase {
        return Room.inMemoryDatabaseBuilder(applicationContext, GameOfThronesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

}