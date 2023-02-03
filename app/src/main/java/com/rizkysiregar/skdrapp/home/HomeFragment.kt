package com.rizkysiregar.skdrapp.home

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.rizkysiregar.skdrapp.R
import com.rizkysiregar.skdrapp.add.AddDataActivity
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.ui.SkdrAdapter
import com.rizkysiregar.skdrapp.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    // binding delegate
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModel()

    // chart init
    lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fab event click
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(requireActivity(), AddDataActivity::class.java))
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.minngu_array,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spPeriodik.adapter = it
        }

        // adapter
        val skdrAdapter = SkdrAdapter()
        with(binding.rvListPeriodeMingguan){
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = skdrAdapter
        }
        // pie chart setting
        pieChart = binding.pieChart
        homeViewModel.skdr.observe(requireActivity()){
            homeViewModel.showChart(pieChart,it)
//            showChart(pieChart,it)
        }

        homeViewModel.setSkdrPeriodic(1)
        binding.btnCariPeriodik.setOnClickListener {
            var periodik = binding.spPeriodik.selectedItem.toString().toInt()
            homeViewModel.setSkdrPeriodic(periodik)
        }
        homeViewModel.skdr.observe(requireActivity()){
            skdrAdapter.setData(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}