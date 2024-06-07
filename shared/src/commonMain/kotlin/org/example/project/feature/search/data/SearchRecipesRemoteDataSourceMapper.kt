package org.example.project.feature.search.data

import org.example.project.feature.search.entities.SearchRecipeModel
import org.example.project.feature.search.data.entities.SearchRecipesResponse

internal class SearchRecipesRemoteDataSourceMapper {
    fun toDomainModel(networkModel: SearchRecipesResponse) = networkModel.searchRecipes.map {
        SearchRecipeModel(
            id = it.id,
            title = it.title,
            image = it.image,
        )
    }
}
