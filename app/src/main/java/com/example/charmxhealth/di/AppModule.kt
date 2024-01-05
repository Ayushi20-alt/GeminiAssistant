package com.example.charmxhealth.di

import android.app.Application
import androidx.room.Room
import com.example.charmxhealth.data.manager.LocalUserManagerImpl
import com.example.charmxhealth.data.repository.HealthRepositoryImp
import com.example.charmxhealth.domain.manager.LocalUserManager
import com.example.charmxhealth.domain.repository.HealthRepository
import com.example.charmxhealth.domain.usecase.app_entry.AppEntryUseCase
import com.example.charmxhealth.domain.usecase.app_entry.ReadAppEntry
import com.example.charmxhealth.domain.usecase.app_entry.SaveAppEntry
import com.example.charmxhealth.presentation.util.Constants.NEWS_DATABASE_NAME
import com.example.webdigitaltask.data.local.HealthDao
import com.example.webdigitaltask.data.local.HealthDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application : Application) : LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCase(localUserManager: LocalUserManager) = AppEntryUseCase(
          readAppEntry = ReadAppEntry(localUserManager),
          saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun providesCarDao(healthDatabase: HealthDatabase) : HealthDao = healthDatabase.carDao()

    @Provides
    @Singleton
    fun proviesCarRepository(
       healthDao: HealthDao
    ): HealthRepository = HealthRepositoryImp(healthDao)

    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application): HealthDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = HealthDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

}