package com.rizkysiregar.skdrapp.core.di

import com.rizkysiregar.skdrapp.add.AddViewModel
import com.rizkysiregar.skdrapp.core.domain.usecase.DataPenyakitInteractor
import com.rizkysiregar.skdrapp.core.domain.usecase.DataPenyakitUseCase
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrInteractor
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase
import com.rizkysiregar.skdrapp.home.HomeViewModel
import com.rizkysiregar.skdrapp.maps.MapsViewModel
import com.rizkysiregar.skdrapp.recapitulation.RecapitulationViewModel
import com.rizkysiregar.skdrapp.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// module obtain all useCase file in project and also provide argument for parameter
val useCaseModule = module {
    factory <SkdrUseCase>{ SkdrInteractor(get()) }
    factory <DataPenyakitUseCase>{ DataPenyakitInteractor(get()) }
}

/*
    viewModelModule to provide argument for viewModel by get() method by inject
    call in MyApplication file for koinStart
*/
val viewModelModule = module {
    viewModel {AddViewModel(get(), get())}
    viewModel { HomeViewModel(get()) }
    viewModel { MapsViewModel(get()) }
    viewModel { RecapitulationViewModel(get()) }
    viewModel { SettingViewModel(get()) }
}