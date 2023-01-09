package com.rizkysiregar.skdrapp.core.data

import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.repository.ISkdrRepository
import kotlinx.coroutines.flow.Flow

class SkdrRepository constructor(
    private val localDataSource: LocalDataSource
) : ISkdrRepository{
    override fun getAllData(): Flow<Resource<Skdr>> {
        TODO("Not yet implemented")
    }

}