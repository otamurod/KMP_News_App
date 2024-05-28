package uz.otamurod.kmp.newsapp.articles.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import uz.otamurod.kmp.newsapp.articles.api.entity.ArticlesResponse
import uz.otamurod.kmp.newsapp.articles.model.Article
import uz.otamurod.kmp.newsapp.util.Constants.BASE_URL

class ArticlesService(private val httpClient: HttpClient) {
    private val country = "us"
    private val category = "technology"
    private val apiKeyProvider = PlatformApiKeyProvider()
    private val apiKey = apiKeyProvider.getApiKey()

    suspend fun fetchArticles(): List<Article> {
        return try {
            val response = httpClient.get(
                urlString = "$BASE_URL?country=$country&apiKey=$apiKey"
            )

            if (response.status.value == 200) {
                val articlesResponse = response.body<ArticlesResponse>()
                articlesResponse.articles.map { articleResponse ->
                    Article(
                        title = articleResponse.title,
                        description = articleResponse.description,
                        date = articleResponse.publishedDate,
                        imageUrl = articleResponse.imageUrl
                    )
                }
            } else {
                println("Error: response.status.value == ${response.status.value}")
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error: exception == ${e.message}")
            emptyList()
        }
    }
}