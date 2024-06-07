package org.example.project.feature.main.data

import org.example.project.feature.main.domain.entities.WineTypeModel

internal class MainRepository(
    private val localDataSource: MainLocalDataSource,
    private val localDataSourceMapper: MainLocalDataSourceMapper,
) {
    suspend fun getWineTypes(): List<WineTypeModel> =
        localDataSource.getWineTypes().map { localDataSourceMapper.toDomainModel(it) }
}
