package com.rizkysiregar.skdrapp.core.di

import com.rizkysiregar.skdrapp.add.AddViewModel
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrInteractor
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase
import com.rizkysiregar.skdrapp.home.HomeViewModel
import com.rizkysiregar.skdrapp.maps.MapsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory <SkdrUseCase>{ SkdrInteractor(get()) }
}

val viewModelModule = module {
    viewModel {AddViewModel(get())}
    viewModel { HomeViewModel(get()) }
    viewModel { MapsViewModel(get()) }
}