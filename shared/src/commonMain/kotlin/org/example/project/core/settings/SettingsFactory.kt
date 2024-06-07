package org.example.project.core.settings

import org.example.project.core.configuration.PlatformConfiguration
import com.russhwolf.settings.Settings

internal expect class SettingsFactory() {

    fun create(
        platformConfiguration: PlatformConfiguration,
        name: String
    ): Settings
}
