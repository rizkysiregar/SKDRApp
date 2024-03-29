package com.rizkysiregar.skdrapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.repository.ISkdrRepository

/*
    extends to SkdrUseCase and override all method on that interface,
    this class need skdrRepository interface as constructor that
    has been inject () by dependency injection Koin in AppModule()
    can find in folder : app/core/AppModule
*/

class SkdrInteractor(private val skdrRepository: ISkdrRepository): SkdrUseCase {
    override fun getAllData(): LiveData<List<Skdr>> = skdrRepository.getAllData()
    override fun insertNewData(skdr: Skdr) = skdrRepository.insertData(skdr)
    override fun getAllDataByPeriodic(periodic: Int) = skdrRepository.getAllDataByPeriodic(periodic)
    override fun getAllDataByNamaDesa(namaDesa: String): LiveData<List<Skdr>> = skdrRepository.getAllDataByNamaDesa(namaDesa)
    override fun deleteData(skdr: Skdr) = skdrRepository.deleteData(skdr)
    override fun deleteAllDataSkdr() = skdrRepository.deleteAllDataSkdr()
}