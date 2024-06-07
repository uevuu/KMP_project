package org.example.project.di.modules

import org.example.project.feature.search.data.SearchRecipesRemoteDataSource
import org.example.project.feature.search.data.SearchRecipesRemoteDataSourceMapper
import org.example.project.feature.search.data.SearchRecipesRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val searchModule = DI.Module("searchModule") {
    bindProvider { SearchRecipesRemoteDataSource(httpClient = instance()) }
    bindProvider {
        SearchRecipesRepository(
            remoteDataSource = instance(),
            remoteDataSourceMapper = SearchRecipesRemoteDataSourceMapper()
        )
    }
}
