package uz.otamurod.kmp.newsapp.feature.sources.di

import org.koin.dsl.module
import uz.otamurod.kmp.newsapp.feature.sources.data.database.datasource.SourcesLocalDataSource
import uz.otamurod.kmp.newsapp.feature.sources.data.network.api.SourcesService
import uz.otamurod.kmp.newsapp.feature.sources.data.repository.SourcesRepository
import uz.otamurod.kmp.newsapp.feature.sources.data.usecase.SourcesUseCase
import uz.otamurod.kmp.newsapp.feature.sources.domain.api.repository.SourcesRepositoryApi
import uz.otamurod.kmp.newsapp.feature.sources.domain.usecase.SourcesUseCaseApi
import uz.otamurod.kmp.newsapp.feature.sources.presentation.SourcesViewModel

val sourcesModule = module {
    single<SourcesService> { SourcesService(get()) }
    single<SourcesUseCaseApi> { SourcesUseCase(get()) }
    single<SourcesViewModel> { SourcesViewModel(get()) }
    single<SourcesLocalDataSource> { SourcesLocalDataSource(get()) }
    single<SourcesRepositoryApi> { SourcesRepository(get(), get()) }
}