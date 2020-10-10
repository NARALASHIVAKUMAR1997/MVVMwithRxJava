package com.example.ctelassign.ui.views

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ctelassign.GPSTracker
import com.example.ctelassign.R
import com.example.ctelassign.ui.adapter.RecyclerViewAdapter
import com.example.ctelassign.ui.viewmodels.VenueModelFactory
import com.example.ctelassign.ui.viewmodels.VenuesModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var venuesModel: VenuesModel
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var venuesModelFactory: VenueModelFactory
    var lat: Double? = null
    var lon: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        venuesModelFactory = VenueModelFactory()
        venuesModel = venuesModelFactory.create(VenuesModel::class.java)
        getLocation()
        sendDatatoRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        getQueryResult()
        setUpObservers()
    }

    fun sendDatatoRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewAdapter = RecyclerViewAdapter(this, emptyList())
        recyclerview_data.apply {
            this.layoutManager = linearLayoutManager
            this.adapter = recyclerViewAdapter
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    if ((ContextCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED)
                    ) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    private fun setUpObservers() {
        venuesModel.liveData.observe(this, Observer {
            recyclerViewAdapter.updateData(it.response!!.venues!!)
        })
    }

    private fun getLocation() {
        val gpsTracker = GPSTracker(this)
        if (gpsTracker.isGPSTrackingEnabled) {
            gpsTracker.getLocation()
            lat = gpsTracker.latitude
            lon = gpsTracker.longitude
            Log.v("lat", lat.toString())
            Log.v("lon", lon.toString())
        }
    }

    private fun getQueryResult() {
        edit_query.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                venuesModel.getdata(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }


}