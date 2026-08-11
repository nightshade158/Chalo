package com.example.chalo.domain.model

data class CartItem(
    val id: Int,
    val listingId: Int,
    val title: String,
    val price: Double,
    val quantity: Int
)