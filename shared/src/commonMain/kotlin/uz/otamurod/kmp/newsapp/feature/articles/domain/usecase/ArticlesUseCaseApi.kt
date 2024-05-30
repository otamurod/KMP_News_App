package uz.otamurod.kmp.newsapp.feature.articles.domain.usecase

import uz.otamurod.kmp.newsapp.feature.articles.domain.api.model.Article

interface ArticlesUseCaseApi {
    suspend fun getArticles(forceFetch: Boolean): List<Article>
}