package com.example.chalo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val listingTitle: String,
    val price: Double,
    val quantity: Int,
    val bookingDate: String,
    val bookingTime: String,
    val status: String = "Pending"
)