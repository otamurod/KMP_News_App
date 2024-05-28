package uz.otamurod.kmp.newsapp.articles.di

import org.koin.dsl.module
import uz.otamurod.kmp.newsapp.articles.ArticlesViewModel
import uz.otamurod.kmp.newsapp.articles.api.ArticlesService
import uz.otamurod.kmp.newsapp.articles.usecase.ArticlesUseCase

val articlesModule = module {
    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
}