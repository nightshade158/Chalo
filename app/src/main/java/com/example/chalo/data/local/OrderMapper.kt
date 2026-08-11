package com.example.chalo.data.local

import com.example.chalo.domain.model.Order

fun OrderEntity.toDomain(): Order {
    return Order(
        id = id,
        listingTitle = listingTitle,
        price = price,
        quantity = quantity,
        bookingDate = bookingDate,
        bookingTime = bookingTime,
        status = status
    )
}