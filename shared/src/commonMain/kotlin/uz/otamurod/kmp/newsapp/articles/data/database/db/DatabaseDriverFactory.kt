package uz.otamurod.kmp.newsapp.articles.data.database.db

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}