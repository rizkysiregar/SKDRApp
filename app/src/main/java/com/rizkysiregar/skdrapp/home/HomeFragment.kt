package com.rizkysiregar.skdrapp.home

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
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
            showChart(pieChart,it)
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

    private fun showChart(pieChart: PieChart, listData: List<Skdr>){
        val arrJumlahPasien: ArrayList<PieEntry> = ArrayList()
        listData.forEach {
            arrJumlahPasien.add(PieEntry(it.jumlahPenderita.toFloat()))
        }

        // on below line we are initializing our
        // variable with their ids.

        // on below line we are setting user percent value,
        // setting description as enabled and offset for pie chart
        pieChart.setUsePercentValues(true)
        pieChart.description.isEnabled = false
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

        // on below line we are setting drag for our pie chart
        pieChart.dragDecelerationFrictionCoef = 0.95f

        // on below line we are setting hole
        // and hole color for pie chart
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)

        // on below line we are setting circle color and alpha
        pieChart.setTransparentCircleColor(Color.WHITE)
        pieChart.setTransparentCircleAlpha(110)

        // on  below line we are setting hole radius
        pieChart.holeRadius = 58f
        pieChart.transparentCircleRadius = 61f

        // on below line we are setting center text
        pieChart.setDrawCenterText(true)

        // on below line we are setting
        // rotation for our pie chart
        pieChart.rotationAngle = 0f

        // enable rotation of the pieChart by touch
        pieChart.isRotationEnabled = true
        pieChart.isHighlightPerTapEnabled = true

        // on below line we are setting animation for our pie chart
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        // on below line we are disabling our legend for pie chart
        pieChart.legend.isEnabled = false
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        // on below line we are setting pie data set
        val dataSet = PieDataSet(arrJumlahPasien, "Mobile OS")

        // on below line we are setting icons.
        dataSet.setDrawIcons(false)

        // on below line we are setting slice for pie
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors to list
        val colors: ArrayList<Int> = ArrayList()

        colors.add(ContextCompat.getColor(
            requireContext(),
            R.color.old_green
            ))
        colors.add(ContextCompat.getColor(
            requireContext(),
            R.color.yellow
        ))
        colors.add(ContextCompat.getColor(
            requireContext(),
            R.color.red
        ))

        // on below line we are setting colors.
        dataSet.colors = colors

        // on below line we are setting pie data set
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.WHITE)
        pieChart.data = data

        // undo all highlights
        pieChart.highlightValues(null)

        // loading chart
        pieChart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}