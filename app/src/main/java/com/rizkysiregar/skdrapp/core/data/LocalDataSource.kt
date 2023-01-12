package com.rizkysiregar.skdrapp.core.data

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity
import com.rizkysiregar.skdrapp.core.data.room.SkdrDao

class LocalDataSource constructor(private val skdrDao: SkdrDao) {
    fun getAllData(): LiveData<List<SkdrEntity>> = skdrDao.getAllData()
    fun insertSkdr(skdrEntity: SkdrEntity) = skdrDao.insertSkdr(skdrEntity)
}