package com.rizkysiregar.skdrapp.core.di

import androidx.room.Room
import com.rizkysiregar.skdrapp.core.data.LocalDataSource
import com.rizkysiregar.skdrapp.core.data.SkdrRepository
import com.rizkysiregar.skdrapp.core.data.room.SkdrDatabase
import com.rizkysiregar.skdrapp.core.domain.repository.IDataPenyakitRepository
import com.rizkysiregar.skdrapp.core.domain.repository.ISkdrRepository
import com.rizkysiregar.skdrapp.core.utils.AppExecutors
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<SkdrDatabase>().skdrDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            SkdrDatabase::class.java, "Skdr.db"
        ).fallbackToDestructiveMigration()
            .createFromAsset("Skdr_db.db")
            .build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single<ISkdrRepository>{SkdrRepository(get(),get())}
    single<IDataPenyakitRepository>{SkdrRepository(get(),get())}
}