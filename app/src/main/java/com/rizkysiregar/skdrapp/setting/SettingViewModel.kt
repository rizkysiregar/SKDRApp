package com.rizkysiregar.skdrapp.setting

import androidx.lifecycle.ViewModel
import com.rizkysiregar.skdrapp.core.di.databaseModule
import com.rizkysiregar.skdrapp.core.domain.model.DataPenyakit
import com.rizkysiregar.skdrapp.core.domain.usecase.DataPenyakitUseCase

class SettingViewModel(private val dataPenyakitUseCase: DataPenyakitUseCase): ViewModel() {
    fun insertNewData(data: DataPenyakit) =
        dataPenyakitUseCase.insertNewDataPenyakit(data)

    val getAllDataPenyakit = dataPenyakitUseCase.getAllDataPenyakit()

    fun deleteData(data: DataPenyakit) =
        dataPenyakitUseCase.deleteDataPenyakit(data)
}