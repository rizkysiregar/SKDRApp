package com.rizkysiregar.skdrapp.add

import androidx.lifecycle.ViewModel
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.usecase.DataPenyakitUseCase
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase

class AddViewModel(private val skdrUseCase: SkdrUseCase, private val dataPenyakitUseCase: DataPenyakitUseCase): ViewModel() {
    fun insertData(skdr: Skdr) =
        skdrUseCase.insertNewData(skdr)
    val getAllData = skdrUseCase.getAllData()

    val getAllDataPenyakit = dataPenyakitUseCase.getAllDataPenyakit()
}