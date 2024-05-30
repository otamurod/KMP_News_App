package uz.otamurod.kmp.newsapp.feature.articles.domain.api.repository

import uz.otamurod.kmp.newsapp.feature.articles.domain.api.model.Article

interface ArticlesRepositoryApi{
    suspend fun getArticles(forceFetch: Boolean): List<Article>
}