package uz.otamurod.kmp.newsapp.feature.articles.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(
    @SerialName("id")
    val id: String?,

    @SerialName("name")
    val name: String
)