package com.example.mandatorymobileapp.model

import java.util.*

 data class Item(val id: Int, val title: String, val price: Double, val description: String, val date: Int, val seller: String, val pictureUrl: String) {
    constructor(title: String, price: Double, description: String, date: Int,seller: String, pictureUrl: String) : this(-1, title, price, description, date, seller, pictureUrl)

    override fun toString(): String {
        return "$id  $title,  $price, $description, $date, $seller, $pictureUrl"
    }
}