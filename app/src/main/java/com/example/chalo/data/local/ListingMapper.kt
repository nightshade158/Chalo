package com.example.chalo.data.local

import com.example.chalo.domain.model.Listing

fun ListingEntity.toDomain(): Listing{
    return Listing(
        id = id,
        title = title,
        description = description,
        price = price,
        category = category
    )
}

fun Listing.toEntity(): ListingEntity{
    return ListingEntity(
        id = id,
        title = title,
        description = description,
        price = price,
        category = category
    )
}