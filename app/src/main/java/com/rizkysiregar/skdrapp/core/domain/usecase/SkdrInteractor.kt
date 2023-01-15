package com.rizkysiregar.skdrapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.repository.ISkdrRepository

class SkdrInteractor(private val skdrRepository: ISkdrRepository): SkdrUseCase {
    override fun getAllData(): LiveData<List<Skdr>> = skdrRepository.getAllData()
    override fun insertNewData(skdr: Skdr) = skdrRepository.insertData(skdr)
    override fun getAllDataByPeriodic(periodic: Int) = skdrRepository.getAllDataByPeriodic(periodic)
}