package org.example.project.core.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.project.AppDatabase
import org.example.project.core.configuration.PlatformConfiguration

internal actual class DriverFactory {

    actual fun createDriver(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): SqlDriver {
        return AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = platformConfiguration.androidContext,
            name = name,
        )
    }
}
