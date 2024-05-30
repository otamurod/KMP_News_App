package uz.otamurod.kmp.newsapp.feature.articles.data.database.mapper

import uz.otamurod.kmp.newsapp.db.ArticleEntity
import uz.otamurod.kmp.newsapp.feature.articles.domain.api.model.Article

object ArticleDbMapper {
    fun fromDto(
        articleEntity: ArticleEntity
    ): Article = with(articleEntity) {
        return Article(
            title = this.title,
            description = this.description!!,
            date = this.date,
            imageUrl = this.imageUrl
        )
    }
}