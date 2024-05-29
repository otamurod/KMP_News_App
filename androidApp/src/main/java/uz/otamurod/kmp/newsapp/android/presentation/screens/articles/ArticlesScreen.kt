package uz.otamurod.kmp.newsapp.android.presentation.screens.articles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import uz.otamurod.kmp.newsapp.android.presentation.screens.articles.components.AppBar
import uz.otamurod.kmp.newsapp.android.presentation.screens.articles.components.ArticlesListView
import uz.otamurod.kmp.newsapp.android.presentation.screens.articles.components.ErrorMessage
import uz.otamurod.kmp.newsapp.articles.presentation.ArticlesViewModel

@Composable
fun ArticlesScreen(
    articlesViewModel: ArticlesViewModel = koinViewModel(),
    onAboutButtonClick: () -> Unit
) {
    val articlesState = articlesViewModel.articlesState.collectAsState()

    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
        AppBar(onAboutButtonClick = onAboutButtonClick)
        if (articlesState.value.error != null) {
            ErrorMessage(articlesState.value.error!!)
        }
        if (articlesState.value.articles.isNotEmpty()) {
            ArticlesListView(articlesViewModel)
        }
    }
}

@Preview(name = "ArticlesScreen")
@Composable
private fun PreviewArticlesScreen() {
    ArticlesScreen(articlesViewModel = koinViewModel(), {})
}