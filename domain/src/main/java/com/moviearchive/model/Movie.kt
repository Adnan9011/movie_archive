package com.moviearchive.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

@Parcelize
data class Movie(
    val title: String,
    val poster: String,
    val overview: String
) : Parcelable {
    val posterLink: String
        get() = "$POSTER_BASE_URL$poster"
}