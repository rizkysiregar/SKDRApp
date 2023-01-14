package com.rizkysiregar.skdrapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.domain.model.DataPenyakit
import com.rizkysiregar.skdrapp.core.domain.repository.IDataPenyakitRepository

class DataPenyakitInteractor(private val dataPenyakitRepository: IDataPenyakitRepository): DataPenyakitUseCase {
    override fun getAllDataPenyakit(): LiveData<List<DataPenyakit>> = dataPenyakitRepository.getAllDataPenyakit()
    override fun insertNewDataPenyakit(dataPenyakit: DataPenyakit) = dataPenyakitRepository.insertData(dataPenyakit)
}