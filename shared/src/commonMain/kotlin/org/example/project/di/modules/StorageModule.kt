package org.example.project.di.modules

import app.cash.sqldelight.ColumnAdapter
import org.example.project.AppDatabase
import org.example.project.core.settings.SettingsFactory
import org.example.project.core.sqldelight.DriverFactory
import com.russhwolf.settings.Settings
import org.example.project.FavouritesDB
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val storageModule = DI.Module(name = "storageModule") {

    bindSingleton<Settings> {
        SettingsFactory().create(
            name = "app_settings",
            platformConfiguration = instance(),
        )
    }

    bindSingleton<AppDatabase> {
        val driver = DriverFactory().createDriver(
            name = "app_database.db",
            platformConfiguration = instance(),
        )
        AppDatabase(
            driver,
            favouritesDBAdapter = FavouritesDB.Adapter(stepsAdapter = listOfStringsAdapter)
        )
    }
}

val listOfStringsAdapter = object : ColumnAdapter<List<String>, String> {
    override fun decode(databaseValue: String) =
        if (databaseValue.isEmpty()) {
            listOf()
        } else {
            databaseValue.split("@")
        }

    override fun encode(value: List<String>) = value.joinToString(separator = "@")
}
