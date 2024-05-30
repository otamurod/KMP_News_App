package uz.otamurod.kmp.newsapp.android.di

import app.cash.sqldelight.db.SqlDriver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import uz.otamurod.kmp.newsapp.db.DatabaseDriverFactory
import uz.otamurod.kmp.newsapp.db.NewsAppDatabase

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }

    single<NewsAppDatabase> { NewsAppDatabase(get()) }
}