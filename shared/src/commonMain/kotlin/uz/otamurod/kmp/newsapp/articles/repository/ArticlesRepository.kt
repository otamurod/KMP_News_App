package uz.otamurod.kmp.newsapp.articles.repository

import uz.otamurod.kmp.newsapp.articles.api.ArticlesService
import uz.otamurod.kmp.newsapp.articles.datasource.ArticlesLocalDataSource
import uz.otamurod.kmp.newsapp.articles.model.Article

class ArticlesRepository(
    private val dataSource: ArticlesLocalDataSource,
    private val service: ArticlesService
) {
    suspend fun getArticles(forceFetch: Boolean): List<Article> {
        if (forceFetch) {
            // Clear Database
            dataSource.deleteAllArticles()

            // Fetch New Articles
            return fetchArticles()
        }

        val localArticles = dataSource.getAllArticles()
        if (localArticles.isEmpty()) {
            return fetchArticles()
        }

        return localArticles
    }

    private suspend fun fetchArticles(): List<Article> {
        val fetchedArticles = service.fetchArticles()
        dataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}