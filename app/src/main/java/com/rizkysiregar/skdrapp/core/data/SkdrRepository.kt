package com.rizkysiregar.skdrapp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.repository.ISkdrRepository
import com.rizkysiregar.skdrapp.core.utils.AppExecutors
import com.rizkysiregar.skdrapp.core.utils.DataMapper

class SkdrRepository constructor(
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ISkdrRepository{
    override fun getAllData(): LiveData<List<Skdr>> {
        return Transformations.map(localDataSource.getAllData()){
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun insertData(skdr: Skdr) {
        val dataSkdr = DataMapper.mapDomainToEntity(skdr)
        appExecutors.diskIO().execute { localDataSource.insertSkdr(dataSkdr) }
    }


}