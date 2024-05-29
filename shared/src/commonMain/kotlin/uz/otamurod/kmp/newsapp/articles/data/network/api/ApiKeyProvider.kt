package uz.otamurod.kmp.newsapp.articles.data.network.api

expect class PlatformApiKeyProvider() {
    fun getApiKey(): String
}