package uz.otamurod.kmp.newsapp.articles.usecase

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import uz.otamurod.kmp.newsapp.articles.model.Article
import uz.otamurod.kmp.newsapp.articles.repository.ArticlesRepository
import kotlin.math.abs

class ArticlesUseCase(private val articlesRepository: ArticlesRepository) {
    suspend fun getArticles(forceFetch: Boolean): List<Article> {
        val articleResponseList = articlesRepository.getArticles(forceFetch)

        return if (articleResponseList.isNotEmpty()) {
            articleResponseList.map { articleResponse ->
                Article(
                    title = articleResponse.title,
                    description = articleResponse.description,
                    date = getFormattedDay(articleResponse.date),
                    imageUrl = articleResponse.imageUrl
                )
            }.filter { article ->
                article.title != "[Removed]"
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