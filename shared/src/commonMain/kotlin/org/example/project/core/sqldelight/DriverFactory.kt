package org.example.project.core.sqldelight

import app.cash.sqldelight.db.SqlDriver
import org.example.project.core.configuration.PlatformConfiguration

internal expect class DriverFactory() {

    fun createDriver(
        name: String,
        platformConfiguration: PlatformConfiguration,
    ): SqlDriver
}
