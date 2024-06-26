package uz.otamurod.kmp.newsapp.feature.articles.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import uz.otamurod.kmp.newsapp.BaseViewModel
import uz.otamurod.kmp.newsapp.feature.articles.domain.usecase.ArticlesUseCaseApi
import uz.otamurod.kmp.newsapp.feature.articles.domain.util.ArticlesState

class ArticlesViewModel(
    private val useCase: ArticlesUseCaseApi
) : BaseViewModel() {
    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
        getArticles()
    }

    fun getArticles(forceFetch: Boolean = false) {
        scope.launch {
            println("ArticlesViewModel:: getArticles(forceFetch = $forceFetch) is called at ${Clock.System.now()}")

            _articlesState.emit(
                ArticlesState(
                    loading = true,
                    articles = _articlesState.value.articles
                )
            )

            val fetchedArticles = useCase.getArticles(forceFetch)
            if (fetchedArticles.isNotEmpty()) {
                _articlesState.emit(ArticlesState(articles = fetchedArticles))
            } else {
                _articlesState.emit(ArticlesState(loading = false, error = "Empty Response!"))
            }
        }
    }
}