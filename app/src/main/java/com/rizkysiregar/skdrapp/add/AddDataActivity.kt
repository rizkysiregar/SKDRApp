package com.rizkysiregar.skdrapp.add


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizkysiregar.skdrapp.R
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.ui.SkdrAdapter
import com.rizkysiregar.skdrapp.databinding.ActivityAddDataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale.Category

class AddDataActivity : AppCompatActivity(){

    // layout binding
    lateinit var binding : ActivityAddDataBinding

    // view model
    private val addViewModel : AddViewModel by viewModel()

    // recycler view
    private lateinit var recyclerView: RecyclerView

    private lateinit var kodePenyakit: String

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

        fillSpinner()
//        ArrayAdapter.createFromResource(
//            this,
//           R.array.penyakit_array,
//            android.R.layout.simple_spinner_item
//        ).also {
//            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            binding.spPenyakit.adapter = it
//        }


        ArrayAdapter.createFromResource(
            this,
            R.array.minngu_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spMinggu.adapter = it
        }

        addViewModel.setDataPenyakitByName("")

        // insert data
        binding.btnSubmit.setOnClickListener {
            setData()
        }

        // recyclerview init
        recyclerView = binding.rvTambahData
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        initAction()
        showRecyclerView()
    }

    private fun showRecyclerView(){
        val skdrAdapter = SkdrAdapter()
        addViewModel.getAllData.observe(this){
            skdrAdapter.setData(it)
        }
        recyclerView.adapter = skdrAdapter
    }

    private fun setData(){
        val namaDesa = binding.spDesa.selectedItem.toString()
        val periodeMinggu = binding.spMinggu.selectedItem.toString().toInt()
        var namaPenyakit = binding.spPenyakit.selectedItem.toString()
        val jumlahPenderita = binding.edtJumlahPasien.text.toString().toInt()
        addViewModel.setDataPenyakitByName(namaPenyakit)
        addViewModel.dataPenyakit.observe(this){
            for (dataPenyakit in it) {
                kodePenyakit = dataPenyakit.kodePenyakit
            }
        }
        try {
            if (kodePenyakit.isEmpty()){
                addViewModel.dataPenyakit.observe(this){
                    for (dataPenyakit in it) {
                        kodePenyakit = dataPenyakit.kodePenyakit
                    }
                }
            }else{
                val skdr = Skdr(0,namaDesa,periodeMinggu,namaPenyakit,kodePenyakit,jumlahPenderita)
                addViewModel.insertData(skdr)
                Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
            }
        }catch(e: Exception){
            Toast.makeText(this,"Error: $e", Toast.LENGTH_LONG).show()
        }
    }

    private fun initAction(){
        val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(0,ItemTouchHelper.RIGHT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val skdr = (viewHolder as SkdrAdapter.ListViewHolder).getSkdr
                addViewModel.deleteData(skdr)
            }

        })
        itemTouchHelper.attachToRecyclerView(binding.rvTambahData)
    }

    private fun fillSpinner(){
        try{
            val dataPenyakit: ArrayList<String> = ArrayList()
            addViewModel.getAllDataPenyakit.observe(this){
                it.forEach {
                    dataPenyakit.add(it.namaPenyakit)
                }
                val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,dataPenyakit)
                binding.spPenyakit.adapter = arrayAdapter
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}


