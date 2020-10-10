package com.example.ctelassign

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class GetLatandLong(val context: Context) {
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    lateinit var mLocation: Location
    var latitude: Double? = null
    var longitude: Double? = null

    fun getLatandLang(): Pair<Double, Double> {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mFusedLocationClient.lastLocation!!.addOnSuccessListener {
                if (it != null) {
                    latitude = it.latitude
                    longitude = it.longitude
                    Log.v("latitude", "$latitude")
                    Log.v("longitude", "$longitude")
                }
            }
        }
        return Pair(latitude!!, longitude!!)
    }

    fun setupPermissions() {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_SMS
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("permission", "permission")
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_SMS
                ) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                Log.e("permission", "permission")
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        context as Activity,
                        Manifest.permission.READ_SMS
                    )
                ) {
                    ActivityCompat.requestPermissions(
                        context,
                        arrayOf(Manifest.permission.READ_SMS), 1
                    )
                } else {
                    ActivityCompat.requestPermissions(
                        context,
                        arrayOf(Manifest.permission.READ_SMS), 1
                    )
                }
            }
        }
    }
}