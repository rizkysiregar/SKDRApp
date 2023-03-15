package com.rizkysiregar.skdrapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// model for DataPenyakitEntity (data)
// Separation of Model
@Parcelize
data class DataPenyakit(
    val kodePenyakit: String,
    val namaPenyakit: String
):Parcelable
