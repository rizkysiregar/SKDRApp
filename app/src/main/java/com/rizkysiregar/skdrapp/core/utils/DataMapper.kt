package com.rizkysiregar.skdrapp.core.utils

import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity
import com.rizkysiregar.skdrapp.core.domain.model.Skdr

object DataMapper {
    fun mapEntitiesToDomain(input: List<SkdrEntity>): List<Skdr> =
        input.map {
            Skdr(
                id = it.id,
                namaDesa = it.namaDesa,
                periodeMinggu = it.periodeMinggu,
                namaPenyakit = it.namaPenyakit,
                kodePenyakit = it.kodePenyakit,
                jumlahPenderita = it.jumlahPenderita
            )
        }

    fun mapDomainToEntity(input: Skdr) = SkdrEntity(
        id = input.id,
        namaDesa = input.namaDesa,
        periodeMinggu = input.periodeMinggu,
        namaPenyakit = input.namaPenyakit,
        kodePenyakit = input.kodePenyakit,
        jumlahPenderita = input.jumlahPenderita
    )
}