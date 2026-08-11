package com.example.chalo.domain.repository

import com.example.chalo.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository{
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun addToCart(listingId: Int, title: String, price: Double)
    suspend fun removeFromCart(item: CartItem)
}