package uz.otamurod.kmp.newsapp.feature.sources.data.usecase

import uz.otamurod.kmp.newsapp.feature.sources.domain.api.model.Source
import uz.otamurod.kmp.newsapp.feature.sources.domain.api.repository.SourcesRepositoryApi
import uz.otamurod.kmp.newsapp.feature.sources.domain.usecase.SourcesUseCaseApi

class SourcesUseCase(private val sourcesRepository: SourcesRepositoryApi) : SourcesUseCaseApi {
    override suspend fun getSources(): List<Source> {
        val sources = sourcesRepository.getAllSources()

        return sources
    }
}