package uz.otamurod.kmp.newsapp.articles.domain.api.model

data class Article(
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String?
)
