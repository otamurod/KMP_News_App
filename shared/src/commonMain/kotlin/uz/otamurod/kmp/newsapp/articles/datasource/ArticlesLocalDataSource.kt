package uz.otamurod.kmp.newsapp.articles.datasource

import uz.otamurod.kmp.newsapp.articles.model.Article
import uz.otamurod.kmp.newsapp.db.NewsAppDatabase

class ArticlesLocalDataSource(
    private val database: NewsAppDatabase
) {
    fun getAllArticles(): List<Article> {
        return database.newsAppDatabaseQueries.selectAllArticles(::mapAsBO).executeAsList()
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

    private fun mapAsBO(
        title: String,
        description: String?,
        date: String,
        imageUrl: String?
    ): Article {
        return Article(title = title, description = description, date = date, imageUrl = imageUrl)
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