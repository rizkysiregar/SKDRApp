package com.rizkysiregar.skdrapp.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.rizkysiregar.skdrapp.R
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.databinding.ActivityAddDataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddDataActivity : AppCompatActivity() {

    // layout binding
    lateinit var binding : ActivityAddDataBinding

    // view model
    private val addViewModel : AddViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ArrayAdapter.createFromResource(
            this,
            R.array.desa_array,
            android.R.layout.simple_spinner_item
        ).also{
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spDesa.adapter = it
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.penyakit_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spPenyakit.adapter = it
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.minngu_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spMinggu.adapter = it
        }

        // insert data
        binding.btnSubmit.setOnClickListener {
            setData()
        }
    }

    private fun setData(){
        val namaDesa = binding.spDesa.selectedItem.toString()
        val periodeMinggu = binding.spMinggu.selectedItem.toString().toInt()
        val namaPenyakit = binding.spPenyakit.selectedItem.toString()
        val kodePenyakit = "A"
        val jumlahPenderita = binding.edtJumlahPasien.text.toString().toInt()
        val skdr = Skdr(0,namaDesa,periodeMinggu,namaPenyakit,kodePenyakit,jumlahPenderita)
        addViewModel.insertData(skdr)
    }
}