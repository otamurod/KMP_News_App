package uz.otamurod.kmp.newsapp.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.otamurod.kmp.newsapp.feature.articles.presentation.ArticlesViewModel
import uz.otamurod.kmp.newsapp.feature.sources.presentation.SourcesViewModel

val viewModelsModule = module {
    viewModel { ArticlesViewModel(get()) }
    viewModel { SourcesViewModel(get()) }
}