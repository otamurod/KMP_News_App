package uz.otamurod.kmp.newsapp.android.screens.articles

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel
import uz.otamurod.kmp.newsapp.articles.ArticlesViewModel
import uz.otamurod.kmp.newsapp.articles.model.Article

@Composable
fun ArticlesScreen(
    articlesViewModel: ArticlesViewModel = koinViewModel(),
    onAboutButtonClick: () -> Unit
) {
    val articlesState = articlesViewModel.articlesState.collectAsState()

    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
        AppBar(onAboutButtonClick = onAboutButtonClick)
        if (articlesState.value.loading) {
            Loader()
        }
        if (articlesState.value.error != null) {
            ErrorMessage(articlesState.value.error!!)
        }
        if (articlesState.value.articles.isNotEmpty()) {
            ArticlesListView(articlesState.value.articles)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(onAboutButtonClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Articles") },
        actions = {
            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "About Device Button"
                )
            }
        }
    )
}

@Composable
fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}

@Composable
fun ArticlesListView(articles: List<Article>) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articles) { article ->
            ArticleItemView(article = article)
        }
    }
}

@Composable
fun ArticleItemView(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ArticleImage(article = article)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.description)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
fun ArticleImage(article: Article) {
    val painter = rememberAsyncImagePainter(model = article.imageUrl)
    val state = painter.state

    when (state) {
        is AsyncImagePainter.State.Loading -> {
            Log.d("ImageState", "Loading image: ${article.imageUrl}")
        }

        is AsyncImagePainter.State.Success -> {
            Log.d("ImageState", "Successfully loaded image: ${article.imageUrl}")
        }

        is AsyncImagePainter.State.Error -> {
            Log.e("ImageError", "Error loading image: ${state.result.throwable}")
        }

        else -> {}
    }

    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(size = 16.dp)),
        model = article.imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Preview(name = "ArticlesScreen")
@Composable
private fun PreviewArticlesScreen() {
    ArticlesScreen(articlesViewModel = koinViewModel(), {})
}