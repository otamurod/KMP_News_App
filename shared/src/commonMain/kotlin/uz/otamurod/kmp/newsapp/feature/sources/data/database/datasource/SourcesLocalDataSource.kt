package uz.otamurod.kmp.newsapp.feature.sources.data.database.datasource

import uz.otamurod.kmp.newsapp.db.NewsAppDatabase
import uz.otamurod.kmp.newsapp.feature.sources.data.database.mapper.SourceDbMapper
import uz.otamurod.kmp.newsapp.feature.sources.domain.api.model.Source

class SourcesLocalDataSource(
    private val database: NewsAppDatabase
) {
    fun getAllSources(): List<Source> =
        database.newsAppDatabaseQueries.selectAllSources().executeAsList().map {
            SourceDbMapper.fromDto(it)
        }

    fun clearSources() =
        database.newsAppDatabaseQueries.removeAllSources()

    fun insertSources(sources: List<Source>) {
        database.newsAppDatabaseQueries.transaction {
            sources.forEach { source ->
                insertSource(source)
            }
        }
    }

    private fun insertSource(source: Source) {
        database.newsAppDatabaseQueries.insertSource(
            source.id,
            source.name,
            source.description,
            source.url,
            source.category,
            source.language,
            source.country,
        )
    }
}