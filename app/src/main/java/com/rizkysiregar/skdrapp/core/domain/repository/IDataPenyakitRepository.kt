package com.rizkysiregar.skdrapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.domain.model.DataPenyakit

interface IDataPenyakitRepository {
    fun getAllDataPenyakit(): LiveData<List<DataPenyakit>>
    fun insertData(dataPenyakit: DataPenyakit)
}