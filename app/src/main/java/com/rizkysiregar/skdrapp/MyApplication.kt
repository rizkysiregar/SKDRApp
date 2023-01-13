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

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
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