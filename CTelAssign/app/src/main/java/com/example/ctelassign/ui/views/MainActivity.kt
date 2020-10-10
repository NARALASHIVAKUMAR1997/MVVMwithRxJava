package com.example.ctelassign.ui.views

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ctelassign.GetLatandLong
import com.example.ctelassign.R
import com.example.ctelassign.ui.adapter.RecyclerViewAdapter
import com.example.ctelassign.ui.viewmodels.VenuesModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var getLatandLong: GetLatandLong
    lateinit var venuesModel: VenuesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLatandLong = GetLatandLong(this@MainActivity)
        getLatandLong.setupPermissions()
        edit_query.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                venuesModel.setData(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }
        })
        sendDatatoRecyclerView()
    }

    fun sendDatatoRecyclerView(){
        val linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        val recyclerViewAdapter = RecyclerViewAdapter(this, emptyList())
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
                            Manifest.permission.READ_SMS
                        ) ==
                                PackageManager.PERMISSION_GRANTED)
                    ) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                        getLatandLong.getLatandLang()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}