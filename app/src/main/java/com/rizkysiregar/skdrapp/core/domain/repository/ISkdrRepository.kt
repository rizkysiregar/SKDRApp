package com.rizkysiregar.skdrapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.domain.model.DataPenyakit
import com.rizkysiregar.skdrapp.core.domain.model.Skdr

// override in SkdrRepository to use all of this function
interface ISkdrRepository {
    fun getAllData(): LiveData<List<Skdr>>
    fun insertData(skdr: Skdr)
    fun getAllDataByPeriodic(periodic: Int): LiveData<List<Skdr>>
    fun deleteData(skdr: Skdr)
    fun getAllDataByNamaDesa(namaDesa: String): LiveData<List<Skdr>>
    fun deleteAllDataSkdr()
}