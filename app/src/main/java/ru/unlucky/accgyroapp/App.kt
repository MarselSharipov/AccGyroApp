package ru.unlucky.accgyroapp

import android.app.Application
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.kpfu.health.di.healthModule
import ru.unlucky.accgyroapp.di.appModule
import ru.unlucky.accgyroapp.di.repoModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(listOf( appModule, repoModule, healthModule))
        }

        RxJavaPlugins.setErrorHandler { it.printStackTrace() }
    }

}