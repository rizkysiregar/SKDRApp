package com.rizkysiregar.skdrapp.core.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rizkysiregar.skdrapp.core.data.entity.DataPenyakitEntity
import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity
import com.rizkysiregar.skdrapp.core.domain.model.DataPenyakit

@Dao
interface SkdrDao {

    // query get all data from table skdr
    @Query("SELECT * FROM skdr ORDER BY id DESC")
    fun getAllData(): LiveData<List<SkdrEntity>>

    // query insert ke table skdr
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSkdr(skdr: SkdrEntity)

    // get data from table dataPenyakit
    @Query("SELECT * FROM data_penyakit")
    fun getAllDataPenyakit(): LiveData<List<DataPenyakitEntity>>

    // insert data to table dataPenyakit
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataPenyakit(dataPenyakit: DataPenyakitEntity)

    // getAll data Natar
    @Query("SELECT * From skdr WHERE nama_desa = 'Natar'")
    fun getDataNatar(): LiveData<List<SkdrEntity>>

}