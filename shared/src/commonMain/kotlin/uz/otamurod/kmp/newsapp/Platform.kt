package uz.otamurod.kmp.newsapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform