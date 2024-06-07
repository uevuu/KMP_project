package org.example.project.core.configuration

import org.example.project.core.binding.FirebaseCrashlyticsBindings

data class Configuration(
    val platformConfiguration: PlatformConfiguration,
    val isHttpLoggingEnabled: Boolean,
    val isDebug: Boolean,
    val firebaseCrashlyticsBindings: FirebaseCrashlyticsBindings,
)
