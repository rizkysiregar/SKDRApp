package com.rizkysiregar.skdrapp.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_penyakit")
data class DataPenyakitEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "kodePenyakit")
    val kodePenyakit: String,

    @ColumnInfo(name = "nama_penyakit")
    val namaPenyakit: String
)
