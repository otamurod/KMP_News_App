package uz.otamurod.kmp.newsapp.android.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uz.otamurod.kmp.newsapp.android.presentation.screens.about.AboutDeviceScreen
import uz.otamurod.kmp.newsapp.android.presentation.screens.articles.ArticlesScreen
import uz.otamurod.kmp.newsapp.android.presentation.screens.sources.SourcesScreen

@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ARTICLES.route,
        modifier = modifier
    ) {
        composable(route = Screen.ARTICLES.route) {
            ArticlesScreen(
                onSourcesButtonClick = {
                    navController.navigate(Screen.SOURCES.route)
                },
                onAboutButtonClick = {
                    navController.navigate(Screen.ABOUT_DEVICE.route)
                }
            )
        }

        composable(route = Screen.SOURCES.route) {
            SourcesScreen(onUpButtonClick = {
                navController.popBackStack()
            })
        }

        composable(route = Screen.ABOUT_DEVICE.route) {
            AboutDeviceScreen(onUpButtonClick = {
                navController.popBackStack()
            })
        }
    }
}

@Preview(name = "AppScaffold")
@Composable
private fun PreviewAppScaffold() {
    AppScaffold()
}