package com.rizkysiregar.skdrapp.add


import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView

import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizkysiregar.skdrapp.R
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.ui.SkdrAdapter
import com.rizkysiregar.skdrapp.databinding.ActivityAddDataBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddDataActivity : AppCompatActivity() {

    // view model
    private val addViewModel : AddViewModel by viewModel()

    // recycler view
    private lateinit var recyclerView: RecyclerView

    // layout binding
    private lateinit var binding : ActivityAddDataBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // assign binding layout
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // populate namaPenyakit from db
        fillSpinner()

        // array adapter for spinner nama desa
        ArrayAdapter.createFromResource(
            this,
            R.array.desa_array,
            android.R.layout.simple_spinner_item
        ).also{
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spDesa.adapter = it
        }

        // array adapter for spinner nama desa
        ArrayAdapter.createFromResource(
            this,
            R.array.minngu_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spMinggu.adapter = it
        }

        // event on button insert onClick then call setInsertData Function
        binding.btnSubmit.setOnClickListener {
            setInsertData()
        }

        // recyclerview init
        recyclerView = binding.rvTambahData
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        initAction()
        showRecyclerView()

        // event on floating action button on click to delete all data in one time
        binding.fabDeleteAll.setOnClickListener {
            deleteAllData()
        }
    }

    // function for insertData to db on call addViewModel.insertData and pass parameter
    private fun setInsertData(){
        val namaDesa = binding.spDesa.selectedItem.toString()
        val periodeMinggu = binding.spMinggu.selectedItem.toString()
        val jumlahPenderita = binding.edtJumlahPasien.text.toString()
        val namaPenyakit = binding.spPenyakit.selectedItem.toString()
        val kodePenyakit = binding.tvKodeAdd.text.toString()
        try {
            if(binding.edtJumlahPasien.text.isEmpty() || jumlahPenderita.toInt() < 1 ){
                Toast.makeText(this,"Upss... kolom jumlah pasien kosong!",Toast.LENGTH_SHORT).show()
            }else{
                val skdr = Skdr(0,namaDesa,periodeMinggu.toInt(),namaPenyakit,kodePenyakit,jumlahPenderita.toInt())
                addViewModel.insertData(skdr)
                Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
                binding.edtJumlahPasien.text.clear()
            }
        }catch(e: Exception){
            Toast.makeText(this,"Error: $e", Toast.LENGTH_LONG).show()
        }
    }

    // fill spinner data from db and get kodePenyakit by namaPenyakit
    private fun fillSpinner(){
        try{
            val dataNamaPenyakit: ArrayList<String> = ArrayList()
            val kode: ArrayList<String> = ArrayList()
            addViewModel.getAllDataPenyakit.observe(this){ it ->
                it.forEach {
                    dataNamaPenyakit.add(it.namaPenyakit)
                    kode.add(it.kodePenyakit)
                }
                val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,dataNamaPenyakit)
                binding.spPenyakit.adapter = arrayAdapter
                binding.spPenyakit.setSelection(arrayAdapter.getPosition("Diare Akut"))
                binding.spPenyakit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                       binding.tvKodeAdd.text = kode.get(position)
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        // do nothing
                    }

                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    /*
    function for obtain skdrAdapter.setData with
    value form addViewModel.getAllData and pass it to
    recyclerview
    */
    private fun showRecyclerView(){
        val skdrAdapter = SkdrAdapter()
        addViewModel.getAllData.observe(this){
            skdrAdapter.setData(it)
        }
        recyclerView.adapter = skdrAdapter
    }


    /*
    function for create gesture when recycler view
    hit by on swipe event and call addViewModel.deleteData
    and that will be delete data by id
     */
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


    /*
        function build to call addViewModel.deleteAllData
        and will be call when FAB hit by on click listener
    */
    private fun deleteAllData(){
        val positiveButtonClick = { _: DialogInterface, _: Int ->
            // try and catch exception to avoid force close error
            try {
                addViewModel.deleteAllDataSkdr()
                Toast.makeText(applicationContext,
                    R.string.alert_success, Toast.LENGTH_SHORT
                ).show()
            }catch(e: Exception){
                Toast.makeText(applicationContext,
                    "$e", Toast.LENGTH_SHORT
                ).show()
            }
        }

        // when alert is canceled
        val negativeButtonClick = { _: DialogInterface, _: Int ->
            Toast.makeText(applicationContext,
                R.string.alert_no, Toast.LENGTH_SHORT
            ).show()
        }

        // show alert dialog to prevent unexpected event
        val builder = AlertDialog.Builder(this)
        with(builder){
            setTitle(R.string.alert)
            setMessage(R.string.alert_message)
            setPositiveButton(R.string.alert_yes, DialogInterface.OnClickListener(function = positiveButtonClick))
            setNegativeButton(R.string.alert_no, DialogInterface.OnClickListener(negativeButtonClick))
            show()
        }
    }

}



