package uz.otamurod.kmp.newsapp.articles.usecase

import uz.otamurod.kmp.newsapp.articles.api.ArticlesService
import uz.otamurod.kmp.newsapp.articles.model.Article

class ArticlesUseCase(private val articlesService: ArticlesService) {
    suspend fun getArticles(): List<Article> {
        val articleResponseList = articlesService.fetchArticles()

        return if (articleResponseList.isNotEmpty()) {
            articleResponseList.map { articleResponse ->
                Article(
                    title = articleResponse.title,
                    description = articleResponse.description ?: "Click to find out more...",
                    date = articleResponse.publishedDate,
                    imageUrl = articleResponse.imageUrl
                        ?: "https://bookninja.com/wp-content/uploads/2022/04/image-6.png?w=900"
                )
            }
        } else {
            println("Error: Empty list fetched!")
            emptyList()
        }
    }
}