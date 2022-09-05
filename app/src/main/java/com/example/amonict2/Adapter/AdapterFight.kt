package com.example.amonict2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.amonict2.R
import com.example.amonict2.databinding.ItemScheduleBinding
import com.example.amonict2.helper.textJD
import com.example.amonict2.model.schedule

class AdapterFight(val context: Context, val listJD: List<schedule>, val date: String) :
    BaseAdapter() {
    override fun getCount(): Int {

        return listJD.size
    }

    override fun getItem(position: Int): Any {
        return listJD.get(position)
    }

    override fun getItemId(position: Int): Long {
        return listJD.get(position).flightNumber.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var binding: ItemScheduleBinding = ItemScheduleBinding.bind(
            LayoutInflater.from(context).inflate(R.layout.item_schedule,parent,false)
        )
        var schedule:schedule = listJD.get(position)
        with(binding){
            txtDate.textJD = date
            txtAircrack.textJD = schedule.aircraft
            txtNumber.textJD = schedule.flightNumber.toString()
            txtPriceFlight.textJD =schedule.price.toString()
            txtTime.textJD = schedule.time


        }
        return binding.root
    }
}