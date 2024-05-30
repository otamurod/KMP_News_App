package uz.otamurod.kmp.newsapp.feature.sources.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("url")
    val url: String,

    @SerialName("category")
    val category: String,

    @SerialName("language")
    val language: String,

    @SerialName("country")
    val country: String
)