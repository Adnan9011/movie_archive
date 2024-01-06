package com.moviearchive.feature.di

import com.moviearchive.feature.presentation.detail.DetailViewModel
import com.moviearchive.feature.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailViewModel(get(), get()) }
}