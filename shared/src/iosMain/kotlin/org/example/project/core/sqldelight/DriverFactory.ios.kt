package org.example.project.core.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.example.project.AppDatabase
import org.example.project.core.configuration.PlatformConfiguration

internal actual class DriverFactory actual constructor() {
    actual fun createDriver(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): SqlDriver {
        return  NativeSqliteDriver(
            schema =  AppDatabase.Schema,
            name = name,
        )
    }
}
