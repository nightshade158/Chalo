package com.example.chalo.domain.model

data class Order(
    val id: Int,
    val listingTitle: String,
    val price: Double,
    val quantity: Int,
    val bookingDate: String,
    val bookingTime: String,
    val status: String
)