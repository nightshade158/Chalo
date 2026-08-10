package com.example.chalo.domain.repository

import com.example.chalo.domain.model.Listing
import kotlinx.coroutines.flow.Flow

interface ListingRepository{
    fun getAllListings(): Flow<List<Listing>>
    suspend fun addListing(listing: Listing)
}