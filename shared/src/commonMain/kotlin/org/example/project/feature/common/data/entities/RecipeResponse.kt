package org.example.project.feature.common.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipeResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("pricePerServing")
    val pricePerServing: Double,
    @SerialName("servings")
    val servings: Int,
    @SerialName("readyInMinutes")
    val readyInMinutes: Int,
    @SerialName("image")
    val image: String? = null,
    @SerialName("title")
    val title: String,
    @SerialName("summary")
    val summary: String,
    @SerialName("analyzedInstructions")
    val analyzedInstructions: List<AnalyzedInstruction>
)

@Serializable
data class Step(
    @SerialName("number")
    val number: Int,
    @SerialName("step")
    val step: String
)

@Serializable
data class AnalyzedInstruction(
    @SerialName("steps")
    val steps: List<Step>
)
