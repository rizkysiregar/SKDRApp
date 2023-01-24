package com.rizkysiregar.skdrapp.core.utils

import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import org.junit.Assert
import org.junit.Test

class DataMapperTest{

    val skdr = Skdr(0, "Natar", 1, "Difteri", "A", 10)
    val skdrEntity = SkdrEntity(0, "Natar", 1, "Difteri", "A", 10)
    val listSkdrEntity = listOf(skdrEntity)
    val listSkdr = listOf(skdr)

    @Test
    fun `mapping skdrDomain class to sdrEntity`(){
        Assert.assertEquals(skdrEntity,DataMapper.mapSkdrDomainToSkdrEntity(skdr))
    }

    @Test
    fun `mapping SkdrEntity to Skdr`(){
        Assert.assertEquals(listSkdr,DataMapper.mapSkdrEntitiesToSkdrDomain(listSkdrEntity))
    }
}