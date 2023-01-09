package com.rizkysiregar.skdrapp.core.data

import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity
import com.rizkysiregar.skdrapp.core.data.room.SkdrDao

class LocalDataSource constructor(private val skdrDao: SkdrDao) {
    suspend fun insertSkdr(skdrEntity: SkdrEntity) = skdrDao.insertSkdr(skdrEntity)
}