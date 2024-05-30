package uz.otamurod.kmp.newsapp.feature.sources.data.database.mapper

import uz.otamurod.kmp.newsapp.db.SourceEntity
import uz.otamurod.kmp.newsapp.feature.sources.domain.api.model.Source

object SourceDbMapper {
    fun fromDto(
        sourceEntity: SourceEntity
    ): Source = with(sourceEntity) {
        return Source(
            id, name, description, url, category, language, country
        )
    }
}