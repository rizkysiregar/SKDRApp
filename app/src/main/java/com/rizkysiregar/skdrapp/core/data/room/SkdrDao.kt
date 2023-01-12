package com.rizkysiregar.skdrapp.core.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity

@Dao
interface SkdrDao {

    // query get all data from table
    @Query("SELECT * FROM skdr")
    fun getAllData(): LiveData<List<SkdrEntity>>

    // query insert ke db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSkdr(skdr: SkdrEntity)

}