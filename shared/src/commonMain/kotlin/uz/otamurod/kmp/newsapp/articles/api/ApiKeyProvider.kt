package uz.otamurod.kmp.newsapp.articles.api

expect class PlatformApiKeyProvider() {
    fun getApiKey(): String
}