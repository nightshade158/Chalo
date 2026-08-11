package com.example.chalo.domain.repository

import com.example.chalo.domain.model.CartItem
import com.example.chalo.domain.model.Order
import kotlinx.coroutines.flow.Flow

interface OrderRepository{
    fun getAllOrders(): Flow<List<Order>>
    suspend fun checkout(cartItems: List<CartItem>, date: String, time: String)
}