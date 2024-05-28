package uz.otamurod.kmp.newsapp.articles

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.otamurod.kmp.newsapp.BaseViewModel
import uz.otamurod.kmp.newsapp.articles.usecase.ArticlesUseCase

class ArticlesViewModel(
    private val useCase: ArticlesUseCase
) : BaseViewModel() {
    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))
    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
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