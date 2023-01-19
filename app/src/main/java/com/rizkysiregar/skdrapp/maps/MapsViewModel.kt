package com.rizkysiregar.skdrapp.maps


import androidx.lifecycle.ViewModel
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase

class MapsViewModel(skdrUseCase: SkdrUseCase): ViewModel() {
    val getAllData = skdrUseCase.getAllData()
}