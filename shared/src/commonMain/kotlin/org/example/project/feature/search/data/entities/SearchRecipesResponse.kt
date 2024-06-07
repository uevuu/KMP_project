package org.example.project.feature.search.data.entities


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchRecipesResponse(
    @SerialName("results")
    val searchRecipes: List<SearchRecipeResponse>
)

@Serializable
data class SearchRecipeResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("image")
    val image: String
)
