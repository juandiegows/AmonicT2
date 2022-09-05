package com.example.amonict2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.amonict2.R
import com.example.amonict2.databinding.ItemAmenitiesBinding
import com.example.amonict2.model.Amenities

class AdapterAmenitiees (val context:Context,val lista:List<Amenities>) : BaseAdapter() {
    override fun getCount(): Int {
        return  lista.size
    }

    override fun getItem(position: Int): Any {
       return  lista.get(position)
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

       var binding:ItemAmenitiesBinding = ItemAmenitiesBinding.bind(LayoutInflater.from(context).inflate(
            R.layout.item_amenities, parent,false))

        var amenities:Amenities = lista.get(position)
        with(binding){
            imgView.setImageResource(amenities.image)
            txtPrice.setText(amenities.price.toString())
            txtTitle.setText(amenities.name)

        }

        return  binding.root
    }

}