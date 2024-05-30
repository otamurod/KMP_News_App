package uz.otamurod.kmp.newsapp.feature.articles.data.network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import uz.otamurod.kmp.newsapp.api.PlatformApiKeyProvider
import uz.otamurod.kmp.newsapp.feature.articles.data.network.entities.ArticlesResponse
import uz.otamurod.kmp.newsapp.feature.articles.domain.api.model.Article
import uz.otamurod.kmp.newsapp.util.Constants.BASE_URL

class ArticlesService(private val httpClient: HttpClient) {
    private val country = "us"
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
                        description = articleResponse.description ?: "Click to find out more...",
                        date = articleResponse.publishedDate,
                        imageUrl = articleResponse.imageUrl
                            ?: "https://bookninja.com/wp-content/uploads/2022/04/image-6.png?w=900"
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