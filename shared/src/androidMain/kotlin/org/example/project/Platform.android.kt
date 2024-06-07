package org.example.project

class AndroidPlatform: Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}