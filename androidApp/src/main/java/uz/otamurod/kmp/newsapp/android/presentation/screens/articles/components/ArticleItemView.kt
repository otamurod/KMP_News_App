package uz.otamurod.kmp.newsapp.android.presentation.screens.articles.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import uz.otamurod.kmp.newsapp.articles.domain.api.model.Article

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