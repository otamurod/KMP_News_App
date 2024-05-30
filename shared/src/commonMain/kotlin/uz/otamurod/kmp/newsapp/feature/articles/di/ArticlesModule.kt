package uz.otamurod.kmp.newsapp.feature.articles.di

import org.koin.dsl.module
import uz.otamurod.kmp.newsapp.feature.articles.data.database.datasource.ArticlesLocalDataSource
import uz.otamurod.kmp.newsapp.feature.articles.data.network.api.ArticlesService
import uz.otamurod.kmp.newsapp.feature.articles.data.repository.ArticlesRepository
import uz.otamurod.kmp.newsapp.feature.articles.data.usecase.ArticlesUseCase
import uz.otamurod.kmp.newsapp.feature.articles.domain.api.repository.ArticlesRepositoryApi
import uz.otamurod.kmp.newsapp.feature.articles.domain.usecase.ArticlesUseCaseApi
import uz.otamurod.kmp.newsapp.feature.articles.presentation.ArticlesViewModel

val articlesModule = module {
    single<ArticlesService> { ArticlesService(get()) }
    single<ArticlesUseCaseApi> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticlesLocalDataSource> { ArticlesLocalDataSource(get()) }
    single<ArticlesRepositoryApi> { ArticlesRepository(get(), get()) }
}