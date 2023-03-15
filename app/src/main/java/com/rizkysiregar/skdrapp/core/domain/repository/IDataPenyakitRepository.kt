package com.rizkysiregar.skdrapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.domain.model.DataPenyakit

// override in SkdrRepository to use all of this function
interface IDataPenyakitRepository {
    fun getAllDataPenyakit(): LiveData<List<DataPenyakit>>
    fun insertData(dataPenyakit: DataPenyakit)
    fun deleteDataPenyakit(dataPenyakit: DataPenyakit)
}