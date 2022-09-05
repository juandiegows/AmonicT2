package com.example.amonict2.model

import com.example.amonict2.R

class Amenities {

    var image: Int = 0
    var name: String = ""
    var price: Double = 1.0

    constructor(image: Int, name: String) {
        this.image = image
        this.name = name
    }

    constructor(image: Int, name: String, price: Double) {
        this.image = image
        this.name = name
        this.price = price
    }

    companion object {

        fun Get(): List<Amenities> {
            var list = ArrayList<Amenities>();
            list.add(Amenities(R.drawable.blanket, "Extra Blanket", 10.0))
            list.add(Amenities(R.drawable.seats, "Next Seat Free", 30.0))
            list.add(Amenities(R.drawable.seats, "Two Neighboring Seats Free", 50.0))
            list.add(Amenities(R.drawable.tablet, "Tablet Rental", 12.0))
            list.add(Amenities(R.drawable.laptop, "Laptop Rental", 15.0))
            list.add(Amenities(R.drawable.lounge, "Lounge Access", 25.0))
            list.add(Amenities(R.drawable.sdrink, "Soft Drinks", 0.0))
            list.add(Amenities(R.drawable.headphone, "Premium Headphones Rental", 5.0))
            list.add(Amenities(R.drawable.wifi, "Wi-Fi 50 mb", 0.0))
            list.add(Amenities(R.drawable.wifi, "Wi-Fi 250 mb", 25.0))
            return list
        }
    }


}