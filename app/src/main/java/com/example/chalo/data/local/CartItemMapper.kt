package com.example.chalo.data.local

import com.example.chalo.domain.model.CartItem

fun CartItemEntity.toDomain(): CartItem{
    return CartItem(
        id = id,
        listingId = listingId,
        title = title,
        price = price,
        quantity = quantity
    )
}

fun CartItem.toEntity(): CartItemEntity{
    return CartItemEntity(
        id = id,
        listingId = listingId,
        title = title,
        price = price,
        quantity = quantity,
    )
}