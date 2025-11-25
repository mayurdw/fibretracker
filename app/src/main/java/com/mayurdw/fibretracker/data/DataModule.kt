package com.mayurdw.fibretracker.data

import android.content.Context
import androidx.room.Room
import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.data.database.AppDatabase
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
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room
            .inMemoryDatabaseBuilder(
                context,
                AppDatabase::class.java
            ).fallbackToDestructiveMigration(false).build()
    }

    @Provides
    fun getFoodEntryDao(database: AppDatabase): AppDao {
        return database.getAppDao()
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
