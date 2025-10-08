package com.mayurdw.fibretracker.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideFoodDatabase(@ApplicationContext context: Context): FoodDatabase {
        return Room
            .inMemoryDatabaseBuilder(
                context,
                FoodDatabase::class.java
            ).fallbackToDestructiveMigration(false).build()
    }

    @Provides
    fun getFoodDao(database: FoodDatabase): FoodDao {
        return database.getFoodDao()
    }
}
