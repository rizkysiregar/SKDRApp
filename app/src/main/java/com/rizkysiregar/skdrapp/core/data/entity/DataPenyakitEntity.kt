package com.rizkysiregar.skdrapp.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/* separation of concern or separation of model
*
* this is implementation that framework used in outside of UI Layer
* for reach Independent of framework
* Library SQLite Room
* model data | for data penyakit
*  */

@Entity(tableName = "data_penyakit")
data class DataPenyakitEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "kode_penyakit")
    val kodePenyakit: String,

    @ColumnInfo(name = "nama_penyakit")
    val namaPenyakit: String
)
