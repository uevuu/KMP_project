package org.example.project.di.modules

import org.example.project.core.binding.FirebaseAnalyticsBinding
import org.example.project.core.binding.FirebaseCrashlyticsBinding
import org.example.project.core.configuration.Configuration
import org.example.project.core.configuration.PlatformConfiguration
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindSingleton
import org.kodein.di.singleton

internal fun createConfigurationModule(configuration: Configuration) = DI.Module(name = "configurationModule") {
    bind<Configuration>() with singleton {
        configuration
    }
    bind<PlatformConfiguration>() with singleton { configuration.platformConfiguration }

    bindSingleton<FirebaseCrashlyticsBinding> {
        configuration.firebaseCrashlyticsBinding
    }

    bindSingleton<FirebaseAnalyticsBinding> {
        configuration.firebaseAnalyticsBinding
    }
}
