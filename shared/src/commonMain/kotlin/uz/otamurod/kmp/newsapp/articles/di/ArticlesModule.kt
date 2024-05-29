package uz.otamurod.kmp.newsapp.articles.di

import org.koin.dsl.module
import uz.otamurod.kmp.newsapp.articles.data.database.datasource.ArticlesLocalDataSource
import uz.otamurod.kmp.newsapp.articles.data.network.api.ArticlesService
import uz.otamurod.kmp.newsapp.articles.data.repository.ArticlesRepository
import uz.otamurod.kmp.newsapp.articles.data.usecase.ArticlesUseCase
import uz.otamurod.kmp.newsapp.articles.domain.api.repository.ArticlesRepositoryApi
import uz.otamurod.kmp.newsapp.articles.domain.usecase.ArticlesUseCaseApi
import uz.otamurod.kmp.newsapp.articles.presentation.ArticlesViewModel

val articlesModule = module {
    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCaseApi> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticlesLocalDataSource> { ArticlesLocalDataSource(get()) }
    single<ArticlesRepositoryApi> { ArticlesRepository(get(), get()) }
}