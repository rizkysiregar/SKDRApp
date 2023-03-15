package com.rizkysiregar.skdrapp.maps

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rizkysiregar.skdrapp.R
import com.rizkysiregar.skdrapp.core.domain.model.Skdr
import com.rizkysiregar.skdrapp.core.ui.SkdrAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.min

class MapsFragment : Fragment() {

    /*
    * viewModel delegate by dependency Inject with Koin
    * init callback for maps on ready and call function
    * show marker and pass to parameter
    **/
    private val mapsViewModel : MapsViewModel by viewModel()
    private val callback = OnMapReadyCallback { googleMap ->
        showMarker(googleMap)
    }

    // obtain google maps interface
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    // mapping data skdr with google maps marker
    private fun showMarker(mMap: GoogleMap){
        // setting map UI setting
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true

        // default marker value
        var markerLocation = LatLng(-5.244242361783416, 105.22078387730636)

        // call mapViewModel to get list data from db
        mapsViewModel.getAllData.observe(this){ list ->
            // iteration using foreach to get all indexes
            list.forEach { map ->
                when(map.namaDesa){
                    "Natar" -> markerLocation = LatLng(-5.241846069680059, 105.24069875772317)
                    "Negara Ratu" -> markerLocation = LatLng(-5.316462360596307, 105.1775949791341)
                    "Rejosari" -> markerLocation = LatLng(-5.285764622848798, 105.15480686620937)
                    "Kalisari" -> markerLocation = LatLng(-5.306940725477708, 105.21995813845814)
                    "Merak Batin" -> markerLocation = LatLng(-5.313570088532476, 105.1968635083637)
                }

                // pass all property
                mMap.addMarker(MarkerOptions().position(markerLocation).title(map.namaDesa).snippet("Kode: ${map.kodePenyakit}, Kasus: ${map.jumlahPenderita}, Minggu Ke: ${map.periodeMinggu}"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(markerLocation))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerLocation, 10f))

                // on marker click show bottom sheet to display recycler view
                mMap.setOnMarkerClickListener {
                    showBottomSheet(it.title.toString())
                    false
                }
            }
        }
    }

    private fun showBottomSheet(title :String) {
        val sheet = BottomSheetDialog(requireContext())
        sheet.setContentView(R.layout.bottom_sheet)
        mapsViewModel.setNamaDesa(title)

    // recycler view init
        val rv = sheet.findViewById<RecyclerView>(R.id.rv_list_periode_mingguan)
        rv?.setHasFixedSize(true)
        rv?.layoutManager = LinearLayoutManager(requireContext())
        val skdrAdapter = SkdrAdapter()
        rv?.adapter = skdrAdapter

        mapsViewModel.skdr.observe(this){
            skdrAdapter.setData(it)
        }

        if (!sheet.isShowing){
            sheet.show()
        }
    }
}