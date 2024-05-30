package uz.otamurod.kmp.newsapp.feature.sources.data.repository

import uz.otamurod.kmp.newsapp.feature.sources.data.database.datasource.SourcesLocalDataSource
import uz.otamurod.kmp.newsapp.feature.sources.data.network.api.SourcesService
import uz.otamurod.kmp.newsapp.feature.sources.domain.api.model.Source
import uz.otamurod.kmp.newsapp.feature.sources.domain.api.repository.SourcesRepositoryApi

class SourcesRepository(
    private val dataSource: SourcesLocalDataSource,
    private val service: SourcesService
) : SourcesRepositoryApi {
    override suspend fun getAllSources(): List<Source> {
        val localSources = dataSource.getAllSources()

        if (localSources.isEmpty()) {
            dataSource.clearSources()

            val fetchedSources = service.fetchSources()
            dataSource.insertSources(fetchedSources)

            return fetchedSources
        }
        return localSources
    }
}