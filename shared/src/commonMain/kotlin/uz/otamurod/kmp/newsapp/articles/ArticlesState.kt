package uz.otamurod.kmp.newsapp.articles

import uz.otamurod.kmp.newsapp.articles.model.Article

data class ArticlesState(
    val articles: List<Article> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)