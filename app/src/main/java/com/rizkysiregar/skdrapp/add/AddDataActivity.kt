package com.rizkysiregar.skdrapp.add


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizkysiregar.skdrapp.R
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.ui.SkdrAdapter
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

        // adapter
        val skdrAdapter = SkdrAdapter()
        addViewModel.getAllData.observe(this){
            skdrAdapter.setData(it)
        }

        with(binding.rvTambahData){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = skdrAdapter
        }
    }

    private fun setData(){
        val namaDesa = binding.spDesa.selectedItem.toString()
        val periodeMinggu = binding.spMinggu.selectedItem.toString().toInt()
        val namaPenyakit = binding.spPenyakit.selectedItem.toString()
        val jumlahPenderita = binding.edtJumlahPasien.text.toString().toInt()
        var kodePenyakit = ""


        when(namaPenyakit){
            "Diare Akut" -> kodePenyakit = "A"
            "Malaria Konfirmasi" -> kodePenyakit = "B"
            "Tersangka Demam Dengue" -> kodePenyakit = "C"
            "Pneumonia" -> kodePenyakit = "D"
            "Diare Berdarah ATAU Disentri" -> kodePenyakit = "E"
            "Tersangka Demam Tifoid" -> kodePenyakit = "F"
            "Sindrom Jaundice Akut" -> kodePenyakit = "G"
            "Tersangka Chikungunya" -> kodePenyakit = "H"
            "Tersangka Flu Burung pada Manusia" -> kodePenyakit = "J"
            "Tersangka Campak" -> kodePenyakit = "K"
            "Tersangka Difteri" -> kodePenyakit = "L"
            "Tersangka Pertussis" -> kodePenyakit = "M"
            "AFP (Lumpuh Layuh Mendadak)" -> kodePenyakit = "N"
            "Kasus Gigitan Hewan Penular Rabies" -> kodePenyakit = "P"
            "Tersangka Antraks" -> kodePenyakit = "Q"
            "Tersangka Leptospirosis" -> kodePenyakit = "R"
            "Tersangka Kolera" -> kodePenyakit = "S"
            "Klaster Penyakit yang tidak lazim" -> kodePenyakit = "T"
            "Tersangka Meningitis/Ensefalitis" -> kodePenyakit = "U"
            "Tersangka Tetanus Neonatorum" -> kodePenyakit = "V"
            "Tersangka Tetanus" -> kodePenyakit = "W"
            "ILI (Influenza Like Ilness)" -> kodePenyakit = "Y"
            "ILI (Tersangka HFMD (Hand, Foot, Mouth Disease)" -> kodePenyakit = "Z"
        }

        try {
            val skdr = Skdr(0,namaDesa,periodeMinggu,namaPenyakit,kodePenyakit,jumlahPenderita)
            addViewModel.insertData(skdr)
            Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
        }catch(e: Exception){
            Toast.makeText(this,"Error: $e", Toast.LENGTH_LONG).show()
        }
    }

}