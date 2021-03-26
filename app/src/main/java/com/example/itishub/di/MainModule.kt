package com.example.itishub.di

import android.content.Context
import com.example.itishub.data.repositories.ContentRepository
import com.example.itishub.data.repositories.ContentRepositoryImpl
import com.example.itishub.data.retrofit.ApiFactory
import com.example.itishub.data.retrofit.ContentService
import com.example.itishub.data.room.ContentDao
import com.example.itishub.data.room.MyDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Singleton
    @Provides
    fun provideContentService(): ContentService = ApiFactory.contentService

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MyDatabase =
        MyDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideContentDao(db: MyDatabase): ContentDao = db.contentDao
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractMainModule {

    @Singleton
    @Binds
    abstract fun provideRepository(repository: ContentRepositoryImpl): ContentRepository
}