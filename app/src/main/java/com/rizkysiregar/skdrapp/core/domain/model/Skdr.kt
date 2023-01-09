package com.rizkysiregar.skdrapp.core.domain.model

import android.os.Parcelable

@Parcelize
data class Skdr(
    val id: Int,
    val namaDesa: String,
    val periodeMinggu: String,
    val namaPenyakit: String,
    val kodePenyakit: String,
    val jumlahPenderita: Int
):
