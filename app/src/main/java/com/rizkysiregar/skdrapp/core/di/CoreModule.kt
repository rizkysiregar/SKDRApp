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
    // single to call SkdrDatabase just only one time
    // its to avoid memory leak 
    single {
        Room.databaseBuilder(
            androidContext(),
            SkdrDatabase::class.java, "Skdr.db" // db name
        ).fallbackToDestructiveMigration()
            .createFromAsset("Skdr_db.db") // from asset
            .build()
    }
}

// call and pass value for  Interface dan class who override it
val repositoryModule = module {
    single { LocalDataSource(get()) }
    factory { AppExecutors() }
    single<ISkdrRepository>{SkdrRepository(get(),get())}
    single<IDataPenyakitRepository>{SkdrRepository(get(),get())}
}