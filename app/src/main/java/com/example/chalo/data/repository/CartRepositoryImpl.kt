package com.example.chalo.data.repository

import com.example.chalo.data.local.CartDao
import com.example.chalo.data.local.CartItemEntity
import com.example.chalo.data.local.toDomain
import com.example.chalo.data.local.toEntity
import com.example.chalo.domain.model.CartItem
import com.example.chalo.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartRepositoryImpl(
    private val dao: CartDao
) : CartRepository{
    override fun getCartItems(): Flow<List<CartItem>> {
        return dao.getAllCartItems().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addToCart(listingId: Int, title: String, price: Double) {
        val existing = dao.getCartItemByListingId(listingId)
        if (existing != null){
            dao.insertCartItem(existing.copy(quantity = existing.quantity + 1))
        }
        else{
            dao.insertCartItem(
                CartItemEntity(listingId = listingId, title = title, price = price, quantity = 1)
            )
        }
    }

    override suspend fun removeFromCart(item: CartItem) {
        dao.deleteCartItem(item.toEntity())
    }
}