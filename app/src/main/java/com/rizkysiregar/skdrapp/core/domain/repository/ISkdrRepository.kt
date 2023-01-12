package com.rizkysiregar.skdrapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.domain.model.Skdr

interface ISkdrRepository {
    fun getAllData(): LiveData<List<Skdr>>
    fun insertData(skdr: Skdr)
}