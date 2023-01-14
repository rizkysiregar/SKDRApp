package com.rizkysiregar.skdrapp.core.utils

import com.rizkysiregar.skdrapp.core.data.entity.DataPenyakitEntity
import com.rizkysiregar.skdrapp.core.data.entity.SkdrEntity
import com.rizkysiregar.skdrapp.core.domain.model.DataPenyakit
import com.rizkysiregar.skdrapp.core.domain.model.Skdr

object DataMapper {
    fun mapSkdrEntitiesToSkdrDomain(input: List<SkdrEntity>): List<Skdr> =
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

    fun mapSkdrDomainToSkdrEntity(input: Skdr) = SkdrEntity(
        id = input.id,
        namaDesa = input.namaDesa,
        periodeMinggu = input.periodeMinggu,
        namaPenyakit = input.namaPenyakit,
        kodePenyakit = input.kodePenyakit,
        jumlahPenderita = input.jumlahPenderita
    )

    fun mapDataPenyakitEntitiesToDomain(input: List<DataPenyakitEntity>): List<DataPenyakit> =
        input.map{
            DataPenyakit(
                kodePenyakit = it.kodePenyakit,
                namaPenyakit = it.namaPenyakit,
                deskripsi = it.deskripsi
            )
        }

    fun  mapDataPenyakitDomainToEntity(input: DataPenyakit) = DataPenyakitEntity(
        kodePenyakit = input.kodePenyakit,
        namaPenyakit = input.namaPenyakit,
        deskripsi = input.deskripsi
    )



}