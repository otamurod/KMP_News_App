package uz.otamurod.kmp.newsapp.di

import uz.otamurod.kmp.newsapp.feature.articles.di.articlesModule
import uz.otamurod.kmp.newsapp.feature.sources.di.sourcesModule

val sharedKoinModules = listOf(
    networkModule,
    articlesModule,
    sourcesModule
)