package uz.otamurod.kmp.newsapp.feature.sources.domain.usecase

import uz.otamurod.kmp.newsapp.feature.sources.domain.api.model.Source

interface SourcesUseCaseApi {
    suspend fun getSources(): List<Source>
}