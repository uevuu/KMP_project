package org.example.project.di.modules

import org.example.project.feature.recommendedWines.data.WinesRemoteDataSource
import org.example.project.feature.recommendedWines.data.WinesRemoteDataSourceMapper
import org.example.project.feature.recommendedWines.data.WinesRepository
import org.example.project.feature.search.data.SearchRecipesRemoteDataSource
import org.example.project.feature.search.data.SearchRecipesRemoteDataSourceMapper
import org.example.project.feature.search.data.SearchRecipesRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val recommendedWinesModule = DI.Module("recommendedWinesModule") {
    bindProvider { WinesRemoteDataSource(httpClient = instance()) }
    bindProvider {
        WinesRepository(
            remoteDataSource = instance(),
            remoteDataSourceMapper = WinesRemoteDataSourceMapper()
        )
    }
}
