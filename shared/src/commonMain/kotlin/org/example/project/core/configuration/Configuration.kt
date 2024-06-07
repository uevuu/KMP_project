package org.example.project.core.configuration

import org.example.project.core.binding.FirebaseAnalyticsBinding
import org.example.project.core.binding.FirebaseCrashlyticsBinding

data class Configuration(
    val platformConfiguration: PlatformConfiguration,
    val isHttpLoggingEnabled: Boolean,
    val isDebug: Boolean,
    val firebaseCrashlyticsBinding: FirebaseCrashlyticsBinding,
    val firebaseAnalyticsBinding: FirebaseAnalyticsBinding,
)
