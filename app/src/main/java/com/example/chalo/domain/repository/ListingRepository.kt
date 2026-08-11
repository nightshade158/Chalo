package com.example.chalo.domain.repository

import androidx.paging.PagingData
import com.example.chalo.domain.model.Listing
import kotlinx.coroutines.flow.Flow

interface ListingRepository{
    fun getAllListings(): Flow<List<Listing>>
    fun getListingsPaged(): Flow<PagingData<Listing>>
    suspend fun addListing(listing: Listing)
}