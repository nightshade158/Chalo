package com.example.chalo.domain.model

data class Listing(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val category: String
)