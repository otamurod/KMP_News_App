package uz.otamurod.kmp.newsapp.articles.data.database.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import uz.otamurod.kmp.newsapp.db.NewsAppDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = NewsAppDatabase.Schema,
            name = "NewsApp.Database.db"
        )
    }
}