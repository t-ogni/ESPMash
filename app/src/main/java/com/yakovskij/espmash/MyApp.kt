package com.yakovskij.espmash
import android.app.Application
import com.yakovskij.espmash.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule) // Подключаем Koin-модули
        }
    }
}
