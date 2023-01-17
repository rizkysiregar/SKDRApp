package com.rizkysiregar.skdrapp.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rizkysiregar.skdrapp.core.data.entity.DataPenyakitEntity
import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity

@Database(entities = [SkdrEntity::class, DataPenyakitEntity::class], version = 1, exportSchema = false)
abstract class SkdrDatabase: RoomDatabase() {
    abstract fun skdrDao(): SkdrDao
}