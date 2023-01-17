package com.rizkysiregar.skdrapp.maps

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rizkysiregar.skdrapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsFragment : Fragment() {

    private val mapsViewModel : MapsViewModel by viewModel()
    private val callback = OnMapReadyCallback { googleMap ->
        showMarker(googleMap)
    }

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

    private fun showMarker(mMap: GoogleMap){
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true
        var markerLocation = LatLng(-5.244242361783416, 105.22078387730636)
        mapsViewModel.getAllData.observe(this){
            it.forEach { map ->

                when(map.namaDesa){
                    "Natar" -> markerLocation = LatLng(-5.241846069680059, 105.24069875772317)
                    "Negara Ratu" -> markerLocation = LatLng(-5.316462360596307, 105.1775949791341)
                    "Rejosari" -> markerLocation = LatLng(-5.285764622848798, 105.15480686620937)
                    "Kalisari" -> markerLocation = LatLng(-5.306940725477708, 105.21995813845814)
                    "Merak Batin" -> markerLocation = LatLng(-5.313570088532476, 105.1968635083637)
                }
                mMap.addMarker(MarkerOptions().position(markerLocation).title(map.namaDesa))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(markerLocation))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerLocation, 10f))
            }
        }

    }
}