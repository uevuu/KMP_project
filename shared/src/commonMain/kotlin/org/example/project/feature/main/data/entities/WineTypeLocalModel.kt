package org.example.project.feature.main.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WineTypeLocalModel(
    @SerialName("title")
    val title: String,
    @SerialName("value")
    val value: String,
    @SerialName("description")
    val description: String,
)
