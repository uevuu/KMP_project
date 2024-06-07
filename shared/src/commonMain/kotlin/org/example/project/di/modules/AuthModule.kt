package org.example.project.di.modules

import org.example.project.feature.common.data.AuthLocalDataSource
import org.example.project.feature.common.data.AuthRepository
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val authModule = DI.Module("authModule") {
    bindProvider {
        AuthLocalDataSource(
            settings = instance(),
            database = instance(),
        )
    }
    bindProvider { AuthRepository(localDataSource = instance()) }
}
