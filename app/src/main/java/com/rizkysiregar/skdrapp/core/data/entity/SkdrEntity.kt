package com.rizkysiregar.skdrapp.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skdr")
data class SkdrEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "nama_desa")
    val namaDesa: String,

    @ColumnInfo(name = "periode_minggu")
    val periodeMinggu: Int,

    @ColumnInfo(name = "nama_penyakit")
    val namaPenyakit: String,

    @ColumnInfo(name = "kode_penyakit")
    val kodePenyakit: String,

    @ColumnInfo(name = "jumlah_penderita")
    val jumlahPenderita: Int

)
