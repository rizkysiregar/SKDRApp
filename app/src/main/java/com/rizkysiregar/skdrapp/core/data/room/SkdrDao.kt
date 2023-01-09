package com.rizkysiregar.skdrapp.core.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity

@Dao
interface SkdrDao {

    // query insert ke db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkdr(skdr: SkdrEntity)

}