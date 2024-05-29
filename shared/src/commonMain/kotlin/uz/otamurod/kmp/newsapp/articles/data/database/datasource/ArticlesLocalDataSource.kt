package uz.otamurod.kmp.newsapp.articles.data.database.datasource

import uz.otamurod.kmp.newsapp.articles.data.database.mapper.ArticleDbMapper
import uz.otamurod.kmp.newsapp.articles.domain.api.model.Article
import uz.otamurod.kmp.newsapp.db.NewsAppDatabase

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