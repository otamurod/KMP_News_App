package uz.otamurod.kmp.newsapp.android.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.otamurod.kmp.newsapp.articles.ArticlesViewModel

val viewModelsModule = module {
    viewModel { ArticlesViewModel(get()) }
}