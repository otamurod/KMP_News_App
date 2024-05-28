package uz.otamurod.kmp.newsapp.di

import uz.otamurod.kmp.newsapp.articles.di.articlesModule

val sharedKoinModules = listOf(
    networkModule,
    articlesModule
)