package com.moviearchive.di

import com.moviearchive.data.di.dataModule
import com.moviearchive.domain.di.domainModule
import com.moviearchive.feature.di.featureModule
import org.koin.dsl.module

object ProvideModules {
    private fun dataModuleProvider() = module {
        includes(dataModule)
    }

    private fun domainModuleProvider() = module {
        includes(domainModule)
    }

    private fun featureModuleProvider() = module {
        includes(featureModule)
    }

    fun getModules() = module {
        includes(
            dataModule,
            domainModule,
            featureModule
        )
    }
}