package uz.otamurod.kmp.newsapp.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = NewsAppDatabase.Schema,
            name = "NewsApp.Database.db"
        )
    }
}