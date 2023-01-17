package com.rizkysiregar.skdrapp.recapitulation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase

class RecapitulationViewModel(skdrUseCase: SkdrUseCase): ViewModel() {
    // getAllDataByPeriodic
    private val _skdrPeriodic = MutableLiveData<Int>()
    private val _skdr = _skdrPeriodic.switchMap { periode ->
        skdrUseCase.getAllDataByPeriodic(periode)
    }

    val skdr: LiveData<List<Skdr>> = _skdr

    fun setSkdrPeriodic(periodic: Int){
        if (periodic == _skdrPeriodic.value){
            return
        }
        _skdrPeriodic.value = periodic
    }
}