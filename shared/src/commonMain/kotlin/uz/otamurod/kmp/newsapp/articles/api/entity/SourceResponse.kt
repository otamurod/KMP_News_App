package uz.otamurod.kmp.newsapp.articles.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(
    @SerialName("id")
    val id: String?,

    @SerialName("name")
    val name: String
)