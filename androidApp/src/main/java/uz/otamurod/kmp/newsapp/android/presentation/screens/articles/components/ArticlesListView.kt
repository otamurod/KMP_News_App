package uz.otamurod.kmp.newsapp.android.presentation.screens.articles.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import uz.otamurod.kmp.newsapp.feature.articles.presentation.ArticlesViewModel

@Composable
fun ArticlesListView(articlesViewModel: ArticlesViewModel) {
    // Show Swipe Refresh Indicator According To The State
    val swipeRefreshState = SwipeRefreshState(articlesViewModel.articlesState.value.loading)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            articlesViewModel.getArticles(forceFetch = true)
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(articlesViewModel.articlesState.value.articles) { article ->
                ArticleItemView(article = article)
            }
        }
    }
}