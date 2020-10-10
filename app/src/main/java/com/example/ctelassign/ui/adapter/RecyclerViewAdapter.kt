package com.example.ctelassign.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ctelassign.R
import com.example.ctelassign.repositary.api.Venue
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.child_item_layout.view.*

class RecyclerViewAdapter(val context: Context, var venuelist: List<Venue>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.child_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val venue = venuelist[position]

        holder.view.name.text = venue.name
        holder.view.address.text = venue.location!!.address
        holder.view.cross_street.text = venue.location!!.crossStreet
        holder.view.city.text = venue.location!!.city
        holder.view.postlcode.text = venue.location!!.postalCode
    }

    override fun getItemCount(): Int = venuelist.size

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    fun updateData(list: List<Venue>){
        this.venuelist = list
        notifyDataSetChanged()
    }
}