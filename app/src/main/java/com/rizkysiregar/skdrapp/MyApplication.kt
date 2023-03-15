package com.rizkysiregar.skdrapp

import android.app.Application
import com.rizkysiregar.skdrapp.core.di.databaseModule
import com.rizkysiregar.skdrapp.core.di.repositoryModule
import com.rizkysiregar.skdrapp.core.di.useCaseModule
import com.rizkysiregar.skdrapp.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

// extend to Application
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        /*
        *   startKoin will be need higher context of application
        *   and pass all modules that will be take care of koin library
        *   to provide context and inject value in field who need them
        * */
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    viewModelModule,
                    repositoryModule,
                    useCaseModule
                )
            )
        }
    }
}