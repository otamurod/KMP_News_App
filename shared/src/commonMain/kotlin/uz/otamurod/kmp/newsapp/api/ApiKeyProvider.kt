package uz.otamurod.kmp.newsapp.api

expect class PlatformApiKeyProvider() {
    fun getApiKey(): String
}