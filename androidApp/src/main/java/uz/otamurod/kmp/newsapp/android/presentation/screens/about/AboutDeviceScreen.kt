package uz.otamurod.kmp.newsapp.android.presentation.screens.about

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import uz.otamurod.kmp.newsapp.android.presentation.screens.about.components.ContentView
import uz.otamurod.kmp.newsapp.android.presentation.screens.about.components.Toolbar

@Composable
fun AboutDeviceScreen(
    onUpButtonClick: () -> Unit
) {
    Column {
        Toolbar(onUpButtonClick = onUpButtonClick)
        ContentView()
    }
}

@Preview(name = "AboutDeviceScreen")
@Composable
private fun PreviewAboutDeviceScreen() {
    AboutDeviceScreen({})
}