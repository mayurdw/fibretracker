package com.mayurdw.fibretracker.data

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
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

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun provideFoodUseCase(foodUseCase: FoodUseCase): IFoodUseCase
}
