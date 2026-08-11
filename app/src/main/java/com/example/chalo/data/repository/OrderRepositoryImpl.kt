package com.example.chalo.data.repository

import com.example.chalo.data.local.OrderDao
import com.example.chalo.data.local.OrderEntity
import com.example.chalo.data.local.toDomain
import com.example.chalo.domain.model.CartItem
import com.example.chalo.domain.model.Order
import com.example.chalo.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OrderRepositoryImpl(
    private val orderDao: OrderDao
) : OrderRepository {

    override fun getAllOrders(): Flow<List<Order>> {
        return orderDao.getAllOrders().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun checkout(cartItems: List<CartItem>, date: String, time: String) {
        for (item in cartItems) {
            orderDao.insertOrder(
                OrderEntity(
                    listingTitle = item.title,
                    price = item.price,
                    quantity = item.quantity,
                    bookingDate = date,
                    bookingTime = time
                )
            )
        }
        orderDao.clearCart()
    }
}