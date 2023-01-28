package com.rizkysiregar.skdrapp.recapitulation

import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SumSameDataTest {
    private val data1 = Skdr(0,"NATAR",1,"DIARE AKUT", "A",12)
    private val data2 = Skdr(0,"REJOSARI",1,"DIARE AKUT", "A",7)
    private val validResult = Skdr(0,"REJOSARI",1,"DIARE AKUT", "A",19)
    private val listData = listOf(data1, data2)

    @Test
    fun sumSameTest() {
        val sum = (listData)
            .groupBy { it.kodePenyakit }
            .values
            .map {
                it.reduce{
                    acc, item -> Skdr(0,item.namaDesa,item.periodeMinggu,item.namaPenyakit,item.kodePenyakit,acc.jumlahPenderita + item.jumlahPenderita)
                }
            }
        Assert.assertEquals(validResult,sum[0])
    }
}