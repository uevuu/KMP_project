package org.example.project.core.settings

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.example.project.core.configuration.PlatformConfiguration


internal actual class SettingsFactory {

    actual fun create(
        platformConfiguration: PlatformConfiguration,
        name: String,
    ): Settings {
        return NSUserDefaultsSettings.Factory().create(name)
    }
}