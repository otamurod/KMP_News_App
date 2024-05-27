package uz.otamurod.kmp.newsapp.articles.api

import uz.otamurod.kmp.newsapp.BuildConfig

actual class PlatformApiKeyProvider {
    actual fun getApiKey(): String {
        // Retrieve from BuildConfig
        return BuildConfig.ARTICLES_API_KEY
    }
}
