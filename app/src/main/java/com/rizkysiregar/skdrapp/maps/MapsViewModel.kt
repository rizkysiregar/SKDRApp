package com.rizkysiregar.skdrapp.maps


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase

class MapsViewModel(skdrUseCase: SkdrUseCase): ViewModel() {

    // get all data from db using skdrUseCase
    val getAllData = skdrUseCase.getAllData()

    // mutable live data for LiveData purpose
    private val _getDataByNamaDesa = MutableLiveData<String>()

    // switch map
    private val _skdr = _getDataByNamaDesa.switchMap { namaDesa ->
        skdrUseCase.getAllDataByNamaDesa(namaDesa)
    }

    // create variable that obtain value from _skdr
    val skdr: LiveData<List<Skdr>> = _skdr

    // set value for _getAllDataByNamaDesa
    fun setNamaDesa(namaDesa: String) {
        if (namaDesa == _getDataByNamaDesa.value){
            return
        }
        _getDataByNamaDesa.value = namaDesa
    }

}