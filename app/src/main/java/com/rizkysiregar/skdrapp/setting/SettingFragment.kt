package com.rizkysiregar.skdrapp.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizkysiregar.skdrapp.core.domain.model.DataPenyakit
import com.rizkysiregar.skdrapp.core.ui.DataPenyakitAdapter
import com.rizkysiregar.skdrapp.databinding.FragmentSettingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    // viewmodel declaration with inject Koin
    private val settingViewModel: SettingViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  when button tambah data is clicked
        binding.btnTambah.setOnClickListener{
            // null pointer check
            if (binding.edtKode.text.isNullOrEmpty() || binding.edtKode.text.isNullOrEmpty() ){
                Toast.makeText(requireActivity(),"Upss.. Ada kolom yang kosong",Toast.LENGTH_SHORT).show()
            }else{
                // get value from UI
                val kode = binding.edtKode.text.toString()
                val penyakit = binding.edtPenyakit.text.toString()

                /*
                *   call function insertNewData from viewmodel
                *   add pass the parameter that is variable data
                * */
                try {
                    val data = DataPenyakit(kode,penyakit)
                    settingViewModel.insertNewData(data)

                    // show toast if success
                    Toast.makeText(requireContext(),"Success", Toast.LENGTH_SHORT).show()

                    // clear view
                    binding.edtKode.text?.clear()
                    binding.edtPenyakit.text?.clear()
                }catch (e: Exception){
                    // show toast if there is error
                    Toast.makeText(requireContext(),"Erorr: $e", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // show recyclerview from adapter
        initAction()
        showRecycler()
    }

    /*
    *   showRecyclerView is function that can get value from viewmodel
    *   and pass it to adapter for recycler view purpose
    *   and also send value from on swipe (delete)
    * */
    private fun showRecycler(){
        val settingAdapter = DataPenyakitAdapter()
        settingViewModel.getAllDataPenyakit.observe(requireActivity()){
            settingAdapter.setData(it)
        }

        with(binding.recyclerview){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = settingAdapter

        }
    }

    /*
    function for create gesture when recycler view
    hit by on swipe event and call settingViewModel.deleteData
    and that will be delete data by id (primary key)
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
                val data = (viewHolder as DataPenyakitAdapter.ListViewHolder).getData
                settingViewModel.deleteData(data)
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)
    }

}