package uz.otamurod.kmp.newsapp.articles.repository

import uz.otamurod.kmp.newsapp.articles.api.ArticlesService
import uz.otamurod.kmp.newsapp.articles.datasource.ArticlesLocalDataSource
import uz.otamurod.kmp.newsapp.articles.model.Article

class ArticlesRepository(
    private val dataSource: ArticlesLocalDataSource,
    private val service: ArticlesService
) {
    suspend fun getArticles(): List<Article> {
        val localArticles = dataSource.getAllArticles()
        if (localArticles.isEmpty()) {
            val fetchedArticles = service.fetchArticles()
            dataSource.insertArticles(fetchedArticles)
            return fetchedArticles
        }

        return localArticles
    }
}