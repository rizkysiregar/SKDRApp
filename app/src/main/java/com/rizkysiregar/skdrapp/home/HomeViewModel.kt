package com.rizkysiregar.skdrapp.home

import androidx.lifecycle.ViewModel
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase

class HomeViewModel(skdrUseCase: SkdrUseCase): ViewModel() {
    val getAllData = skdrUseCase.getAllData()
}