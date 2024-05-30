package uz.otamurod.kmp.newsapp.feature.articles.domain.util

import uz.otamurod.kmp.newsapp.feature.articles.domain.api.model.Article

data class ArticlesState(
    val articles: List<Article> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)