package uz.otamurod.kmp.newsapp.api

import platform.Foundation.NSProcessInfo

actual class PlatformApiKeyProvider {
    actual fun getApiKey(): String {
        // Retrieve from Environment variables
        val apiKey = NSProcessInfo.processInfo.environment["API_KEY"] as? String
        println("PlatformApiKeyProvider:: API Key: $apiKey")
        return apiKey ?: ""
    }
}
