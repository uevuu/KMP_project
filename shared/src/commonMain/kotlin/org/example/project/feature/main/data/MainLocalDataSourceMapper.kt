package org.example.project.feature.main.data

import org.example.project.feature.main.data.entities.WineTypeLocalModel
import org.example.project.feature.main.domain.entities.WineTypeModel

internal class MainLocalDataSourceMapper {

    fun toDomainModel(dataModel: WineTypeLocalModel) = dataModel.let {
        WineTypeModel(
            title = it.title,
            value = it.value,
            description = it.description,
        )
    }
}
