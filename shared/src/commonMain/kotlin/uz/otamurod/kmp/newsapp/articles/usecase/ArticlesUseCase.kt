package uz.otamurod.kmp.newsapp.articles.usecase

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import uz.otamurod.kmp.newsapp.articles.api.ArticlesService
import uz.otamurod.kmp.newsapp.articles.model.Article
import kotlin.math.abs

class ArticlesUseCase(private val articlesService: ArticlesService) {
    suspend fun getArticles(): List<Article> {
        val articleResponseList = articlesService.fetchArticles()

        return if (articleResponseList.isNotEmpty()) {
            articleResponseList.map { articleResponse ->
                Article(
                    title = articleResponse.title,
                    description = articleResponse.description ?: "Click to find out more...",
                    date = getFormattedDay(articleResponse.publishedDate),
                    imageUrl = articleResponse.imageUrl
                        ?: "https://bookninja.com/wp-content/uploads/2022/04/image-6.png?w=900"
                )
            }
        } else {
            println("Error: Empty list fetched!")
            emptyList()
        }
    }

    private fun getFormattedDay(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}