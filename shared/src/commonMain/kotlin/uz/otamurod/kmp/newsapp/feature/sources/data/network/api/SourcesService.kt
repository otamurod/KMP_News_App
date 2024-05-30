package uz.otamurod.kmp.newsapp.feature.sources.data.network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import uz.otamurod.kmp.newsapp.api.PlatformApiKeyProvider
import uz.otamurod.kmp.newsapp.feature.sources.data.network.entities.SourcesResponse
import uz.otamurod.kmp.newsapp.feature.sources.domain.api.model.Source
import uz.otamurod.kmp.newsapp.util.Constants.BASE_URL

class SourcesService(private val httpClient: HttpClient) {
    private val apiKeyProvider = PlatformApiKeyProvider()
    private val apiKey = apiKeyProvider.getApiKey()

    suspend fun fetchSources(): List<Source> {
        return try {
            val response = httpClient.get(
                urlString = "$BASE_URL/sources?apiKey=$apiKey"
            )

            if (response.status.value == 200) {
                val sourcesResponse = response.body<SourcesResponse>()
                sourcesResponse.sources.map { sourceResponse ->
                    Source(
                        id = sourceResponse.id,
                        name = sourceResponse.name,
                        description = sourceResponse.description,
                        url = sourceResponse.url,
                        category = sourceResponse.category,
                        language = sourceResponse.language,
                        country = sourceResponse.country
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