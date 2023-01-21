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


        binding.btnTambah.setOnClickListener{
            if (binding.edtKode.text.isNullOrEmpty() || binding.edtKode.text.isNullOrEmpty() ){
                Toast.makeText(requireActivity(),"Upss.. Ada kolom yang kosong",Toast.LENGTH_SHORT).show()
            }else{
                val kode = binding.edtKode.text.toString()
                val penyakit = binding.edtPenyakit.text.toString()
                try {
                    val data = DataPenyakit(kode,penyakit)
                    settingViewModel.insertNewData(data)
                    Toast.makeText(requireContext(),"Success", Toast.LENGTH_SHORT).show()
                    binding.edtKode.text?.clear()
                    binding.edtPenyakit.text?.clear()
                }catch (e: Exception){
                    Toast.makeText(requireContext(),"Erorr: $e", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // show recyclerview from adapter
        initAction()
        showRecycler()
    }

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