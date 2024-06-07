package org.example.project.di.modules

import org.example.project.feature.recipeDetails.data.RecipeDetailsRemoteDataSource
import org.example.project.feature.recipeDetails.data.RecipeDetailsRemoteDataSourceMapper
import org.example.project.feature.recipeDetails.data.RecipeDetailsRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val recipeDetailsModule = DI.Module("recipeDetailsModule") {
    bindProvider { RecipeDetailsRemoteDataSource(httpClient = instance()) }
    bindProvider {
        RecipeDetailsRepository(
            remoteDataSource = instance(),
            remoteDataSourceMapper = RecipeDetailsRemoteDataSourceMapper()
        )
    }
}
