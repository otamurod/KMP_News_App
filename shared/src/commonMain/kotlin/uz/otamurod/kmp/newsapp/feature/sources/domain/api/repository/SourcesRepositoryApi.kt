package uz.otamurod.kmp.newsapp.feature.sources.domain.api.repository

import uz.otamurod.kmp.newsapp.feature.sources.domain.api.model.Source

interface SourcesRepositoryApi {
    suspend fun getAllSources(): List<Source>
}