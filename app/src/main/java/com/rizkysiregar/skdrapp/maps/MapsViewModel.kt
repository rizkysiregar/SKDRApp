package com.rizkysiregar.skdrapp.maps


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase

class MapsViewModel(skdrUseCase: SkdrUseCase): ViewModel() {
    val getAllData = skdrUseCase.getAllData()

    private val _getDataByNamaDesa = MutableLiveData<String>()
    private val _skdr = _getDataByNamaDesa.switchMap { namaDesa ->
        skdrUseCase.getAllDataByNamaDesa(namaDesa)
    }

    val skdr: LiveData<List<Skdr>> = _skdr

    fun setNamaDesa(namaDesa: String) {
        if (namaDesa == _getDataByNamaDesa.value){
            return
        }
        _getDataByNamaDesa.value = namaDesa
    }

}