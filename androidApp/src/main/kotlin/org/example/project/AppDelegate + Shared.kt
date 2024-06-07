package org.example.project

import android.os.Build
import org.example.project.core.binding.FirebaseAnalyticsBinding
import org.example.project.core.binding.FirebaseCrashlyticsBinding
import org.example.project.core.configuration.Configuration
import org.example.project.core.configuration.PlatformConfiguration
import org.example.project.di.PlatformSDK

fun AppDelegate.initShared() {
    val config = Configuration(
        platformConfiguration = PlatformConfiguration(
            androidContext = applicationContext,
            appVersionName = BuildConfig.VERSION_NAME,
            appVersionNumber = BuildConfig.VERSION_CODE.toString(),
            osVersion = Build.VERSION.RELEASE.toString(),
        ),
        isDebug = BuildConfig.DEBUG,
        isHttpLoggingEnabled = BuildConfig.DEBUG,
        firebaseCrashlyticsBinding = object : FirebaseCrashlyticsBinding {
            override fun nonFatal(error: Throwable) {}
            override fun setParams(key: String, value: String) {}
        },
        firebaseAnalyticsBinding = AndroidFirebaseAnalyticsBinding()
    )
    PlatformSDK.init(conf = config)
}
