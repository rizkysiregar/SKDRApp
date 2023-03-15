package com.rizkysiregar.skdrapp.add

import androidx.lifecycle.*
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.usecase.DataPenyakitUseCase
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase
import com.rizkysiregar.skdrapp.databinding.ActivityAddDataBinding

class AddViewModel(private val skdrUseCase: SkdrUseCase, dataPenyakitUseCase: DataPenyakitUseCase): ViewModel() {

    /*
        insertData function need skdr as parameter to run properly
        and call skdrUseCase in order to get access and call insertNewData
    */
    fun insertData(skdr: Skdr) =
        skdrUseCase.insertNewData(skdr)

    /*
        getAllData is a function to get all data that has been
         entered by surveillance
    */
    val getAllData = skdrUseCase.getAllData()

    /*
        getAllDataPenyakit is ada table which contain
        all information of listed disease for spinner data
    */
    val getAllDataPenyakit = dataPenyakitUseCase.getAllDataPenyakit()

    // delete data require skdr as parameter on call delete skdr data by id next in skdrDao
    fun deleteData(skdr: Skdr){
        skdrUseCase.deleteData(skdr)
    }

    // function to delete all data form table skdr
    fun deleteAllDataSkdr(){
        skdrUseCase.deleteAllDataSkdr()
    }

}

