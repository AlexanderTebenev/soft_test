package com.example.soft

import android.app.Application
import com.example.soft.di.myModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class QrCodeApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@QrCodeApplication)
            // modules
            modules(myModule)
        }
    }
}