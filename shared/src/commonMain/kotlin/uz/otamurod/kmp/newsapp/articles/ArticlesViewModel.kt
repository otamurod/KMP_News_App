package uz.otamurod.kmp.newsapp.articles

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import uz.otamurod.kmp.newsapp.BaseViewModel
import uz.otamurod.kmp.newsapp.articles.api.ArticlesService
import uz.otamurod.kmp.newsapp.articles.usecase.ArticlesUseCase

class ArticlesViewModel : BaseViewModel() {
    private val useCase: ArticlesUseCase
    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        val articlesService = ArticlesService(httpClient)
        useCase = ArticlesUseCase(articlesService)
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            val fetchedArticles = useCase.getArticles()
            if (fetchedArticles.isNotEmpty()) {
                _articlesState.emit(ArticlesState(articles = fetchedArticles))
            } else {
                _articlesState.emit(ArticlesState(loading = false, error = "Empty Response!"))
            }
        }
    }
}