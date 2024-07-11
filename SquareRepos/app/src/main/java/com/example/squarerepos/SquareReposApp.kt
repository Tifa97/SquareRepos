package com.example.squarerepos

import android.app.Application
import com.example.squarerepos.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// Application class needed for using Koin
class SquareReposApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@SquareReposApp)
            modules(appModule)
        }
    }
}