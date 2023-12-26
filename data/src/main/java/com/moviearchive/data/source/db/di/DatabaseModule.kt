package com.moviearchive.data.source.db.di

import android.content.Context
import androidx.room.Room
import com.moviearchive.data.source.db.DatabaseSource
import com.moviearchive.data.source.db.util.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        DatabaseSource::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideMovieDao(db: DatabaseSource) = db.movieDao()

    @Singleton
    @Provides
    fun provideCommentDao(db: DatabaseSource) = db.commentDao()
}