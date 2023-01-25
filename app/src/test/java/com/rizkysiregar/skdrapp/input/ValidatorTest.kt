package com.rizkysiregar.skdrapp.input

import com.google.common.truth.Truth.assertThat
import com.rizkysiregar.skdrapp.core.utils.Validator
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest {
    @Test
    fun whenInputIsValid(){
        val namaDaerah = "Natar"
        val namaPenyakit = "Tersangka Kolera"
        val kodePenyakit = "A"
        val jumlahPenderita = 10
        val data = Validator.validateInput(namaDaerah,namaPenyakit,kodePenyakit,jumlahPenderita)
        assertThat(data).isEqualTo(true)
    }
    @Test
    fun whenInputIsInValid() {
        val namaDaerah = ""
        val namaPenyakit = ""
        val kodePenyakit = ""
        val jumlahPenderita = 0
        val data = Validator.validateInput(namaDaerah, namaPenyakit, kodePenyakit, jumlahPenderita)
        assertThat(data).isEqualTo(false)
    }
}
