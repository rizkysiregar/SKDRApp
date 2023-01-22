package com.rizkysiregar.skdrapp.domain

import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity

object DataDummy {
    fun generateDummySkdrEntity(): List<SkdrEntity>{
        val skdrList = ArrayList<SkdrEntity>()

        for (i in 0..10){
            val data = SkdrEntity(
                0,
                "Natar",
                1,
                "Malaria",
                "A",
                1
            )
            skdrList.add(data)
        }
        return skdrList
    }
}