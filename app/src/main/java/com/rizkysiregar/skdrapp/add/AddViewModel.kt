package com.rizkysiregar.skdrapp.add

import androidx.lifecycle.*
import com.rizkysiregar.skdrapp.core.domain.model.DataPenyakit
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.usecase.DataPenyakitUseCase
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase

class AddViewModel(private val skdrUseCase: SkdrUseCase, private val dataPenyakitUseCase: DataPenyakitUseCase): ViewModel() {
    fun insertData(skdr: Skdr) =
        skdrUseCase.insertNewData(skdr)
    val getAllData = skdrUseCase.getAllData()
    val getAllDataPenyakit = dataPenyakitUseCase.getAllDataPenyakit()
    fun deleteData(skdr: Skdr){
        skdrUseCase.deleteData(skdr)
    }
    private val _dataPenyakitByName = MutableLiveData<String>()
    private val _dataPenyakit = _dataPenyakitByName.switchMap { name ->
        dataPenyakitUseCase.getDataByName(name)
    }

    val dataPenyakit: LiveData<List<DataPenyakit>> = _dataPenyakit

    fun setDataPenyakitByName(name: String){
        if (name == _dataPenyakitByName.value){
            return
        }
        _dataPenyakitByName.value = name
    }
}