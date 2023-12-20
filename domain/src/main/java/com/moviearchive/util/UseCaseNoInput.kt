package com.moviearchive.util

interface UseCaseNoInput<Output> {
    suspend operator fun invoke(): Output
}