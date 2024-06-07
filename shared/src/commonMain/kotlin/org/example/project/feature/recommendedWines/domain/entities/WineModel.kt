package org.example.project.feature.recommendedWines.domain.entities

data class WineModel(
    val id: Int,
    val title: String,
    val averageRating: Double,
    val description: String,
    val imageUrl: String,
    val link: String,
    val price: String,
    val ratingCount: Double,
    val score: Double
)
