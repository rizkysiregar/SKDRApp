package com.rizkysiregar.skdrapp.home

import android.graphics.Color
import android.graphics.Typeface
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.domain.usecase.SkdrUseCase

class HomeViewModel(skdrUseCase: SkdrUseCase): ViewModel() {
    // getAllDataByPeriodic
    private val _skdrPeriodic = MutableLiveData<Int>()
    private val _skdr = _skdrPeriodic.switchMap { periode ->
        skdrUseCase.getAllDataByPeriodic(periode)
    }

    val skdr: LiveData<List<Skdr>> = _skdr

    fun setSkdrPeriodic(periodic: Int){
        if (periodic == _skdrPeriodic.value){
            return
        }
        _skdrPeriodic.value = periodic
    }

     fun showChart(pieChart: PieChart, listData: List<Skdr>){
        val arrJumlahPasien: ArrayList<PieEntry> = ArrayList()
        listData.forEach {
            arrJumlahPasien.add(PieEntry(it.jumlahPenderita.toFloat(),it.namaDesa))
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
        pieChart.centerText = "Persebaran"
        // on below line we are setting pie data set
        val dataSet = PieDataSet(arrJumlahPasien, "Grafik")

        // on below line we are setting icons.
        dataSet.setDrawIcons(false)

        // on below line we are setting slice for pie
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a lot of colors to list
        val colors: ArrayList<Int> = ArrayList()

        colors.add(Color.parseColor("#F20000"))
        colors.add(Color.parseColor("#FF9800"))
        colors.add(Color.parseColor("#FFEB3B"))
        colors.add(Color.parseColor("#00FF0A"))
        colors.add(Color.parseColor("#5F3901"))

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


}