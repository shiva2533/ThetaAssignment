package com.example.thetatechnolabassignment.home.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.thetatechnolabassignment.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    companion object {
        const val REQUEST_CODE = 123
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.mapContainer) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let {
            mMap = it
        }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_CODE
            )
        } else {

            mMap.isMyLocationEnabled = true
            val locatonManager: LocationManager =
                activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            val locationGps: Location? =
                locatonManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            val locationNetwork: Location? =
                locatonManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (locationGps != null) {
                val marker = LatLng(locationGps.longitude, locationGps.longitude)
                addMarker(mMap, marker)
            } else if (locationNetwork != null) {
                val marker = LatLng(locationNetwork.longitude, locationNetwork.longitude)
                addMarker(mMap, marker)


            }

        }

    }

    private fun addMarker(googleMap: GoogleMap?, latLng: LatLng) {
        googleMap?.addMarker(
            MarkerOptions()
                .position(latLng)
                .title("My location")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }

}




