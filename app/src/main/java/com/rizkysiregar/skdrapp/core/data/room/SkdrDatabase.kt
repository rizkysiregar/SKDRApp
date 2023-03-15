package com.rizkysiregar.skdrapp.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rizkysiregar.skdrapp.core.data.entity.DataPenyakitEntity
import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity

/*
*
* Database initialisation with Room SQLite Library which
* contain database name, database data class that used to be table
* add call skdrDao for custom query to database
* */
@Database(entities = [SkdrEntity::class, DataPenyakitEntity::class], version = 2, exportSchema = false)
abstract class SkdrDatabase: RoomDatabase() {
    abstract fun skdrDao(): SkdrDao
}