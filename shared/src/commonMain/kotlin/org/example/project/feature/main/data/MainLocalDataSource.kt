package org.example.project.feature.main.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.example.project.feature.main.data.entities.WineTypeLocalModel
import org.example.project.feature.main.data.entities.wineTypes

internal class MainLocalDataSource(
    private val json: Json,
) {
    suspend fun getWineTypes(): List<WineTypeLocalModel> = withContext(Dispatchers.Default) {
        json.decodeFromString(wineTypes)
    }
}
