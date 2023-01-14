package com.rizkysiregar.skdrapp.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase

class MapsViewModel(skdrUseCase: SkdrUseCase): ViewModel() {
    val getAllData = skdrUseCase.getAllData()
}