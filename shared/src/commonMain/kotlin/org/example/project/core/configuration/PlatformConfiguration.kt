package org.example.project.core.configuration

expect class PlatformConfiguration {
    val appVersionName: String
    val appVersionNumber: String
    val osVersion: String
}
