package com.example.chalo.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao{
    @Query("SELECT * FROM cart_items")
    fun getAllCartItems():
            Flow<List<CartItemEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(item: CartItemEntity)

    @Delete
    suspend fun deleteCartItem(item: CartItemEntity)

    @Query("SELECT * FROM cart_items WHERE listingID = :listingId LIMIT 1")
    suspend fun getCartItemByListingId(listingId: Int): CartItemEntity?
}