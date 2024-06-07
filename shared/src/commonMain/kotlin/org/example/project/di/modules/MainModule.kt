package org.example.project.di.modules

import org.example.project.feature.main.data.MainLocalDataSource
import org.example.project.feature.main.data.MainLocalDataSourceMapper
import org.example.project.feature.main.data.MainRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val mainModule = DI.Module("mainModule") {
    bindProvider<MainLocalDataSource> { MainLocalDataSource(json = instance()) }
    bindProvider { MainLocalDataSourceMapper() }
    bindSingleton<MainRepository> {
        MainRepository(
            localDataSource = instance(),
            localDataSourceMapper = instance()
        )
    }
}
