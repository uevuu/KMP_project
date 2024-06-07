package org.example.project.core.binding

interface FirebaseCrashlyticsBinding {

    fun nonFatal(error: Throwable)

    fun setParams(key: String, value: String)
}
