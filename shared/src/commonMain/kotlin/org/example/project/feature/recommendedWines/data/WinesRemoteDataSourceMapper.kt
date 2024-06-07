package org.example.project.feature.recommendedWines.data

import org.example.project.feature.recommendedWines.data.entities.RecommendedWinesResponse
import org.example.project.feature.recommendedWines.domain.entities.WineModel

internal class WinesRemoteDataSourceMapper {
    fun toDomainModel(networkModel: RecommendedWinesResponse) = networkModel.recommendedWines.map {
        WineModel(
            id = it.id,
            title = it.title,
            averageRating = it.averageRating,
            description = it.description.orEmpty(),
            imageUrl = it.imageUrl,
            link = it.link,
            price = it.price,
            ratingCount = it.ratingCount,
            score = it.score
        )
    }
}
