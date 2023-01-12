package com.rizkysiregar.skdrapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Skdr(
    val id: Int,
    val namaDesa: String,
    val periodeMinggu: Int,
    val namaPenyakit: String,
    val kodePenyakit: String,
    val jumlahPenderita: Int
): Parcelable
