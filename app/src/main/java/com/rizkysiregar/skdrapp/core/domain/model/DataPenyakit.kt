package com.rizkysiregar.skdrapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DataPenyakit(
    val kodePenyakit: String,
    val namaPenyakit: String
):Parcelable
