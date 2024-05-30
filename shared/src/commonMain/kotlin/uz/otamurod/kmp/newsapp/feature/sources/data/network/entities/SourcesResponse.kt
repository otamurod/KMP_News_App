package uz.otamurod.kmp.newsapp.feature.sources.data.network.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourcesResponse(
    @SerialName("status")
    val status: String,

    @SerialName("sources")
    val sources: List<SourceResponse>
)