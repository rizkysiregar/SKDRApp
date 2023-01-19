package com.rizkysiregar.skdrapp.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizkysiregar.skdrapp.R
import com.rizkysiregar.skdrapp.core.domain.model.DataPenyakit
import com.rizkysiregar.skdrapp.core.ui.DataPenyakitAdapter
import com.rizkysiregar.skdrapp.databinding.FragmentSettingBinding
import com.rizkysiregar.skdrapp.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment() : Fragment() {

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

        // show recyclerview from adapter
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

}