package uz.otamurod.kmp.newsapp.android.presentation.screens.sources

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import uz.otamurod.kmp.newsapp.android.presentation.screens.sources.components.ErrorMessage
import uz.otamurod.kmp.newsapp.android.presentation.screens.sources.components.SourceItemView
import uz.otamurod.kmp.newsapp.android.presentation.screens.sources.components.Toolbar
import uz.otamurod.kmp.newsapp.feature.sources.presentation.SourcesViewModel

@Composable
fun SourcesScreen(
    onUpButtonClick: () -> Unit,
    viewModel: SourcesViewModel = koinViewModel(),
) {
    Column {
        Toolbar(onUpButtonClick = onUpButtonClick)
        val sourcesState = viewModel.sourcesState.collectAsState()

        if (sourcesState.value.error != null) {
            ErrorMessage(sourcesState.value.error!!)
        }
        if (sourcesState.value.sources.isNotEmpty()) {
            SourcesListView(viewModel)
        }
    }
}

@Composable
fun SourcesListView(viewModel: SourcesViewModel) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(viewModel.sourcesState.value.sources) { source ->
            SourceItemView(source = source)
        }
    }
}

@Preview(name = "SourcesScreen")
@Composable
private fun PreviewSourcesScreen() {
    SourcesScreen({})
}