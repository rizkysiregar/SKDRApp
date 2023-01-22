package com.rizkysiregar.skdrapp.core.utils

object Validator {
    fun validateInput(namaDaerah: String, namaPenyakit: String, kodePenyakit: String, jumlahPenderita: Int ): Boolean{
        return !(namaDaerah.isEmpty() || namaPenyakit.isEmpty() || kodePenyakit.isEmpty() || jumlahPenderita <= 0)
    }
}