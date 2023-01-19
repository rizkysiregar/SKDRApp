package com.rizkysiregar.skdrapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.domain.model.DataPenyakit

interface DataPenyakitUseCase {
    fun getAllDataPenyakit(): LiveData<List<DataPenyakit>>
    fun insertNewDataPenyakit(dataPenyakit: DataPenyakit)
    fun deleteDataPenyakit(dataPenyakit: DataPenyakit)
}