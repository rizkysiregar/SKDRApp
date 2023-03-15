package com.rizkysiregar.skdrapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
// an interface were be extends in SkdrInteractor
interface SkdrUseCase {
    fun getAllData(): LiveData<List<Skdr>>
    fun insertNewData(skdr: Skdr)
    fun getAllDataByPeriodic(periodic: Int): LiveData<List<Skdr>>
    fun getAllDataByNamaDesa(namaDesa: String): LiveData<List<Skdr>>
    fun deleteData(skdr:Skdr)
    fun deleteAllDataSkdr()
}