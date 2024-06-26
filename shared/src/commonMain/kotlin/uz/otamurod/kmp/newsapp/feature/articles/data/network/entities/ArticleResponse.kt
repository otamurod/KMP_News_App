package uz.otamurod.kmp.newsapp.feature.articles.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    @SerialName("source")
    val source: SourceResponse,

    @SerialName("author")
    val author: String?,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String?,

    @SerialName("url")
    val url: String,

    @SerialName("urlToImage")
    val imageUrl: String?,

    @SerialName("publishedAt")
    val publishedDate: String,

    @SerialName("content")
    val content: String?
)