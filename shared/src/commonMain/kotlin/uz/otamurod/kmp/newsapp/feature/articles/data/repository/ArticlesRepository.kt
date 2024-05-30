package uz.otamurod.kmp.newsapp.feature.articles.data.repository

import kotlinx.datetime.Clock
import uz.otamurod.kmp.newsapp.feature.articles.data.network.api.ArticlesService
import uz.otamurod.kmp.newsapp.feature.articles.domain.api.model.Article
import uz.otamurod.kmp.newsapp.feature.articles.domain.api.repository.ArticlesRepositoryApi

class ArticlesRepository(
    private val dataSource: uz.otamurod.kmp.newsapp.feature.articles.data.database.datasource.ArticlesLocalDataSource,
    private val service: ArticlesService
) : ArticlesRepositoryApi {
    override suspend fun getArticles(forceFetch: Boolean): List<Article> {
        if (forceFetch) {
            // Clear Database
            dataSource.deleteAllArticles()
            println("ArticlesRepository:: Local DB is cleared at ${Clock.System.now()}")

            // Fetch New Articles
            println("ArticlesRepository:: getArticles(forceFetch = $forceFetch) is called via [forceFetch] at ${Clock.System.now()}")
            return fetchArticles()
        }

        val localArticles = dataSource.getAllArticles()
        println("ArticlesRepository:: getArticles(forceFetch = $forceFetch) accessed DB at ${Clock.System.now()}")

        if (localArticles.isEmpty()) {
            println("ArticlesRepository:: fetchArticles(forceFetch = $forceFetch) is called at ${Clock.System.now()}")

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