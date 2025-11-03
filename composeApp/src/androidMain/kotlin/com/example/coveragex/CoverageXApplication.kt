package com.example.coveragex

import android.app.Application
import com.example.coveragex.di.presentationModule
import com.example.coveragex.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class for Android.
 * Initializes Koin dependency injection.
 */
class CoverageXApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CoverageXApplication)
            modules(sharedModule, presentationModule)
        }
    }
}
