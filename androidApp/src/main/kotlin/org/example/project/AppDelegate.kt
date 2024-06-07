package org.example.project

import android.app.Application

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        initShared()
    }
}
