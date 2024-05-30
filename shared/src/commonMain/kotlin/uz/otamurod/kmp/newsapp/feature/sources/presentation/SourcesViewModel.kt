package uz.otamurod.kmp.newsapp.feature.sources.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.otamurod.kmp.newsapp.BaseViewModel
import uz.otamurod.kmp.newsapp.feature.sources.domain.usecase.SourcesUseCaseApi
import uz.otamurod.kmp.newsapp.feature.sources.domain.util.SourcesState

class SourcesViewModel(
    private val useCase: SourcesUseCaseApi
) : BaseViewModel() {
    private val _sourcesState: MutableStateFlow<SourcesState> =
        MutableStateFlow(SourcesState(loading = true))
    val sourcesState: StateFlow<SourcesState> get() = _sourcesState

    init {
        getSources()
    }

    fun getSources() {
        scope.launch {
            _sourcesState.emit(
                SourcesState(
                    loading = true,
                    sources = _sourcesState.value.sources
                )
            )

            val fetchedArticles = useCase.getSources()
            if (fetchedArticles.isNotEmpty()) {
                _sourcesState.emit(SourcesState(sources = fetchedArticles))
            } else {
                _sourcesState.emit(SourcesState(loading = false, error = "Empty Response!"))
            }
        }
    }
}