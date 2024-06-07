package org.example.project.di.modules

import org.example.project.feature.common.data.RecipesRemoteDataSource
import org.example.project.feature.common.data.RecipesRemoteDataSourceMapper
import org.example.project.feature.common.data.RecipesRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val recipesModule = DI.Module("recipesModule") {
    bindProvider { RecipesRemoteDataSource(httpClient = instance()) }
    bindProvider { RecipesRemoteDataSourceMapper() }
    bindSingleton<RecipesRepository> {
        RecipesRepository(
            remoteDataSource = instance(),
            remoteDataSourceMapper = instance()
        )
    }
}
