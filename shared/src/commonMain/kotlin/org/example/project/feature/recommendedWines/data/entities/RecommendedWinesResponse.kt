package org.example.project.feature.recommendedWines.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedWinesResponse(
    @SerialName("recommendedWines")
    val recommendedWines: List<WineResponse>
)

@Serializable
data class WineResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("averageRating")
    val averageRating: Double,
    @SerialName("description")
    val description: String?,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("link")
    val link: String,
    @SerialName("price")
    val price: String,
    @SerialName("ratingCount")
    val ratingCount: Double,
    @SerialName("score")
    val score: Double
)
