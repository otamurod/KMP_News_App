package uz.otamurod.kmp.newsapp.android.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.otamurod.kmp.newsapp.Platform

@Composable
fun AboutDeviceScreen(
    onUpButtonClick: () -> Unit
) {
    Column {
        Toolbar(onUpButtonClick = onUpButtonClick)
        ContentView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(onUpButtonClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                "About Device",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        }
    )
}

@Composable
private fun ContentView() {
    val itemsList = getItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        items(itemsList) { row ->
            RowView(title = row.first, subTitle = row.second)
        }
    }
}

fun getItems(): List<Pair<String, String>> {
    val platform = Platform()
    platform.logSystemInfo()

    return listOf(
        Pair("Operating System", "${platform.osName} ${platform.osVersion}"),
        Pair("Device", platform.deviceModel),
        Pair("Density", platform.density.toString())
    )
}

@Composable
private fun RowView(
    title: String,
    subTitle: String
) {
    Column(
        modifier = Modifier.padding(all = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Start,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = subTitle,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start,
        )
        Divider(modifier = Modifier.padding(horizontal = 0.dp, vertical = 4.dp))
    }
}

@Preview(name = "AboutDeviceScreen")
@Composable
private fun PreviewAboutDeviceScreen() {
    AboutDeviceScreen({})
}