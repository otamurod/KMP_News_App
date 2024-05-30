package uz.otamurod.kmp.newsapp.feature.articles.data.database.datasource

import uz.otamurod.kmp.newsapp.db.NewsAppDatabase
import uz.otamurod.kmp.newsapp.feature.articles.data.database.mapper.ArticleDbMapper
import uz.otamurod.kmp.newsapp.feature.articles.domain.api.model.Article

class ArticlesLocalDataSource(
    private val database: NewsAppDatabase
) {
    fun getAllArticles(): List<Article> {
        return database.newsAppDatabaseQueries.selectAllArticles().executeAsList().map {
            ArticleDbMapper.fromDto(it)
        }
    }

    fun insertArticles(articles: List<Article>) {
        database.newsAppDatabaseQueries.transaction {
            articles.forEach { article: Article ->
                insertArticle(article)
            }
        }
    }

    fun deleteAllArticles() {
        database.newsAppDatabaseQueries.deleteAllArticles()
    }

    private fun insertArticle(article: Article) {
        database.newsAppDatabaseQueries.insertArticle(
            article.title,
            article.description,
            article.date,
            article.imageUrl
        )
    }
}