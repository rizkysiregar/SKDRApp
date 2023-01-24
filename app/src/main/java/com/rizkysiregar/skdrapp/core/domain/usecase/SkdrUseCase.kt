package com.rizkysiregar.skdrapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.domain.model.Skdr

interface SkdrUseCase {
    fun getAllData(): LiveData<List<Skdr>>
    fun insertNewData(skdr: Skdr)
    fun getAllDataByPeriodic(periodic: Int): LiveData<List<Skdr>>
    fun deleteData(skdr:Skdr)
}