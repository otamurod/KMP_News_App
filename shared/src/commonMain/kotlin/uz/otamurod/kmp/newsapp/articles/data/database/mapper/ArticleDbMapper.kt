package uz.otamurod.kmp.newsapp.articles.data.database.mapper

import uz.otamurod.kmp.newsapp.articles.domain.api.model.Article
import uz.otamurod.kmp.newsapp.db.ArticleEntity

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