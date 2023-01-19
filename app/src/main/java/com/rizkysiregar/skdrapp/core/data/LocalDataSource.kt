package com.rizkysiregar.skdrapp.core.data

import androidx.lifecycle.LiveData
import com.rizkysiregar.skdrapp.core.data.entity.DataPenyakitEntity
import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity
import com.rizkysiregar.skdrapp.core.data.room.SkdrDao
import com.rizkysiregar.skdrapp.core.domain.model.Skdr

class LocalDataSource constructor(private val skdrDao: SkdrDao) {
    fun getAllData(): LiveData<List<SkdrEntity>> = skdrDao.getAllData()
    fun insertSkdr(skdrEntity: SkdrEntity) = skdrDao.insertSkdr(skdrEntity)
    fun getAllDataPenyakit(): LiveData<List<DataPenyakitEntity>> = skdrDao.getAllDataPenyakit()
    fun insertDataPenyakit(dataPenyakitEntity: DataPenyakitEntity) = skdrDao.insertDataPenyakit(dataPenyakitEntity)
    fun getAllDataByPeriodic(periodic: Int): LiveData<List<SkdrEntity>> = skdrDao.getDataByPeriode(periodic)
    fun deleteDataPenyakit(data: DataPenyakitEntity) = skdrDao.deleteDataPenyakit(data)
    fun deleteDataSkdr(skdrEntity: SkdrEntity) = skdrDao.deleteDataSkdr(skdrEntity)
}