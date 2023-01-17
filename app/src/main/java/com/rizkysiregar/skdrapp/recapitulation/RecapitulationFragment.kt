package com.rizkysiregar.skdrapp.recapitulation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizkysiregar.skdrapp.R
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.ui.SkdrAdapter
import com.rizkysiregar.skdrapp.databinding.FragmentRecapitulationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecapitulationFragment : Fragment() {

    // binding delegate
    private var _binding: FragmentRecapitulationBinding? = null
    private val binding get() = _binding!!
    private val recapitulationViewModel: RecapitulationViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecapitulationBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set spinner data
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.minngu_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spMingguRecap.adapter = it
        }

        // Adapter RecyclerView
        val skdrAdapter = SkdrAdapter()
        with(binding.recyclerView){
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = skdrAdapter
        }

        // get data by periodic (weekly)
        recapitulationViewModel.setSkdrPeriodic(1)
        binding.btnCari.setOnClickListener {
            var periodic = binding.spMingguRecap.selectedItem.toString().toInt()
            recapitulationViewModel.setSkdrPeriodic(periodic)
        }
        val sb = StringBuilder()

        recapitulationViewModel.skdr.observe(requireActivity()) {
            skdrAdapter.setData(it)
            sb.clear()
            sb.append("SKDR ${binding.spMingguRecap.selectedItem}#2023")
            for (skdr in it){
              sb.append("#${skdr.kodePenyakit}${skdr.jumlahPenderita}")
            }
            binding.tvFormatLaporan.text = sb
        }
    }


}