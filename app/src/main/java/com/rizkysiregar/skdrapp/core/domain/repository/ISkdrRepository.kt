package com.rizkysiregar.skdrapp.core.domain.repository

import com.rizkysiregar.skdrapp.core.data.Resource
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import kotlinx.coroutines.flow.Flow

interface ISkdrRepository {
    fun getAllData(): Flow<Resource<Skdr>>
}