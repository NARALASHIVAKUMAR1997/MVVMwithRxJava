package com.example.ctelassign

import android.Manifest
import android.app.AlertDialog
import android.app.Service
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.util.*


class GPSTracker(private val mContext: Context) : Service(),
    LocationListener {
    var isGPSEnabled = false
    var isNetworkEnabled = false
    var isGPSTrackingEnabled = false
    var location: Location? = null
    var latitude = 0.0
    var longitude = 0.0
    protected var locationManager: LocationManager? = null
    private var provider_info: String? = null

    fun getLocation(){
        try {
            locationManager = mContext.getSystemService(LOCATION_SERVICE) as LocationManager
            isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
            isNetworkEnabled = locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if (isGPSEnabled) {
                isGPSTrackingEnabled = true
                Log.d(TAG, "Application use GPS Service")
               provider_info = LocationManager.GPS_PROVIDER
            } else if (isNetworkEnabled) { // Try to get location if you Network Service is enabled
                isGPSTrackingEnabled = true
                Log.d(TAG, "Application use Network State to get GPS coordinates")
            }
            if (!provider_info!!.isEmpty()) {
                if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    locationManager!!.requestLocationUpdates(
                        provider_info!!,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(),
                        this
                    )
                    if (locationManager != null) {
                        location = locationManager!!.getLastKnownLocation(provider_info)
                        updateGPSCoordinates()
                    }
                    return
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Impossible to connect to LocationManager", e)
        }
    }

    fun updateGPSCoordinates() {
        if (location != null) {
            latitude = location!!.latitude
            longitude = location!!.longitude
        }
    }
    fun stopUsingGPS() {
        if (locationManager != null) {
            locationManager!!.removeUpdates(this@GPSTracker)
        }
    }

    override fun onLocationChanged(location: Location) {}
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {
        private val TAG = GPSTracker::class.java.name

        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10 // 10 meters

        private const val MIN_TIME_BW_UPDATES = 1000 * 60 * 1 // 1 minute
            .toLong()
    }

    init {
        getLocation()
    }
}