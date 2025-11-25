package com.mayurdw.fibretracker.data

import android.content.Context
import androidx.room.Room
import com.mayurdw.fibretracker.data.database.EntryDao
import com.mayurdw.fibretracker.data.database.EntryDatabase
import com.mayurdw.fibretracker.data.database.FoodDao
import com.mayurdw.fibretracker.data.database.FoodDatabase
import com.mayurdw.fibretracker.data.usecase.AddEntryUseCase
import com.mayurdw.fibretracker.data.usecase.FoodUseCase
import com.mayurdw.fibretracker.data.usecase.GetEntryUseCase
import com.mayurdw.fibretracker.data.usecase.IAddEntryUseCase
import com.mayurdw.fibretracker.data.usecase.IFoodUseCase
import com.mayurdw.fibretracker.data.usecase.IGetEntryUseCase
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

    @Provides
    @Singleton
    fun provideEntityDatabase(@ApplicationContext context: Context): EntryDatabase {
        return Room
            .inMemoryDatabaseBuilder(
                context,
                EntryDatabase::class.java
            )
            .fallbackToDestructiveMigration(false).build()
    }

    @Provides
    fun getEntryDao(database: EntryDatabase): EntryDao {
        return database.getEntryDao()
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun provideFoodUseCase(foodUseCase: FoodUseCase): IFoodUseCase

    @Binds
    abstract fun provideAddEntryUseCase(addEntryUseCase: AddEntryUseCase): IAddEntryUseCase

    @Binds
    abstract fun provideGetEntryUseCase(getEntryUseCase: GetEntryUseCase): IGetEntryUseCase
}
