package com.moviearchive.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moviearchive.data.source.db.dao.CommentDao
import com.moviearchive.data.source.db.dao.MovieDao
import com.moviearchive.data.source.db.model.CommentDatabaseModel
import com.moviearchive.data.source.db.model.MovieDatabaseModel
import com.moviearchive.data.source.db.util.DATABASE_VERSION

@Database(
    entities = [MovieDatabaseModel::class, CommentDatabaseModel::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class DatabaseSource : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun commentDao(): CommentDao
}