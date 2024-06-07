package org.example.project.di.modules

import org.example.project.feature.common.data.FavouritesLocalDataSource
import org.example.project.feature.common.data.FavouritesLocalDataSourceMapper
import org.example.project.feature.common.data.FavouritesRepository
import org.example.project.feature.search.data.SearchRecipesRemoteDataSource
import org.example.project.feature.search.data.SearchRecipesRemoteDataSourceMapper
import org.example.project.feature.search.data.SearchRecipesRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val favouriteRecipesModule = DI.Module("favouriteRecipesModule") {
    bindProvider { FavouritesLocalDataSource(database = instance()) }
    bindProvider {
        FavouritesRepository(
            localDataSource = instance(),
            localDataSourceMapper = FavouritesLocalDataSourceMapper()
        )
    }
}
