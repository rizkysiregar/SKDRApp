package com.rizkysiregar.skdrapp.core.domain.model

data class Skdr(
    val id: Int,
    val namaDesa: String,
    val periodeMinggu: Int,
    val namaPenyakit: String,
    val kodePenyakit: String,
    val jumlahPenderita: Int
)
