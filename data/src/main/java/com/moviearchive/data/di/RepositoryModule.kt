package com.moviearchive.data.di

import com.moviearchive.data.repository.MovieRepository
import com.moviearchive.data.repository.MovieRepositoryImpl
import com.moviearchive.data.source.api.api.ApiServiceImpl
import com.moviearchive.data.source.datastore.DataStoreSource
import com.moviearchive.data.source.db.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        api: ApiServiceImpl,
        dao: MovieDao,
        dataStore: DataStoreSource
    ): MovieRepository = MovieRepositoryImpl(
        api = api,
        dao = dao,
        dataStore = dataStore
    )
}