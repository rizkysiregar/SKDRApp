package com.rizkysiregar.skdrapp.add

import androidx.lifecycle.*
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.usecase.DataPenyakitUseCase
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase
import com.rizkysiregar.skdrapp.databinding.ActivityAddDataBinding

class AddViewModel(private val skdrUseCase: SkdrUseCase, dataPenyakitUseCase: DataPenyakitUseCase): ViewModel() {

    fun insertData(skdr: Skdr) =
        skdrUseCase.insertNewData(skdr)

    val getAllData = skdrUseCase.getAllData()

    val getAllDataPenyakit = dataPenyakitUseCase.getAllDataPenyakit()

    fun deleteData(skdr: Skdr){
        skdrUseCase.deleteData(skdr)
    }

    fun deleteAllDataSkdr(){
        skdrUseCase.deleteAllDataSkdr()
    }

}

