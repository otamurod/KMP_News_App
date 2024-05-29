package uz.otamurod.kmp.newsapp.di

import app.cash.sqldelight.db.SqlDriver
import org.koin.dsl.module
import uz.otamurod.kmp.newsapp.articles.data.database.db.DatabaseDriverFactory
import uz.otamurod.kmp.newsapp.db.NewsAppDatabase

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }

    single<NewsAppDatabase> { NewsAppDatabase(get()) }
}