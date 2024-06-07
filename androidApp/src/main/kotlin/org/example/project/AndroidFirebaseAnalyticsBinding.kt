package org.example.project

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.ktx.Firebase
import org.example.project.core.binding.FirebaseAnalyticsBinding

class AndroidFirebaseAnalyticsBinding: FirebaseAnalyticsBinding {

    private val analytics by lazy {
        Firebase.analytics
    }

    override fun logScreenLoaded(screenName: String) {
        analytics.logEvent(SCREEN_OPENED) {
            param(SCREEN_NAME, screenName)
        }
    }

    private companion object {
        const val SCREEN_OPENED = "screen_opened"
        const val SCREEN_NAME = "screen_name"
    }
}
