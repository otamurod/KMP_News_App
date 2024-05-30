package uz.otamurod.kmp.newsapp.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import uz.otamurod.kmp.newsapp.feature.articles.presentation.ArticlesViewModel
import uz.otamurod.kmp.newsapp.feature.sources.presentation.SourcesViewModel

fun initKoin() {
    val modules = sharedKoinModules + databaseModule

    startKoin {
        modules(modules = modules)
    }
}

class ArticlesInjector : KoinComponent {
    val articlesViewModel: ArticlesViewModel by inject()
}

class SourcesInjector : KoinComponent {

    val sourcesViewModel: SourcesViewModel by inject()
}