package uz.otamurod.kmp.newsapp.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import uz.otamurod.kmp.newsapp.articles.presentation.ArticlesViewModel

fun initKoin() {
    val modules = sharedKoinModules + databaseModule

    startKoin {
        modules(modules = modules)
    }
}

class ArticlesInjector : KoinComponent {
    val articlesViewModel: ArticlesViewModel by inject()
}