package uz.otamurod.kmp.newsapp.android.presentation.screens.articles.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onSourcesButtonClick: () -> Unit,
    onAboutButtonClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Articles") },
        actions = {
            IconButton(onClick = onSourcesButtonClick) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Sources List Button"
                )
            }
            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "About Device Button"
                )
            }
        }
    )
}