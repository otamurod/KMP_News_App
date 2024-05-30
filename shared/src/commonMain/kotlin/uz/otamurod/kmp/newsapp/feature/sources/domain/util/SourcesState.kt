package uz.otamurod.kmp.newsapp.feature.sources.domain.util

import uz.otamurod.kmp.newsapp.feature.sources.domain.api.model.Source

data class SourcesState(
    val sources: List<Source> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)
