package com.example.chalo.data.repository

import com.example.chalo.data.local.ListingDao
import com.example.chalo.data.local.toDomain
import com.example.chalo.data.local.toEntity
import com.example.chalo.domain.model.Listing
import com.example.chalo.domain.repository.ListingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ListingRepositoryImpl(
    private val dao: ListingDao
) : ListingRepository{
    override fun getAllListings(): Flow<List<Listing>> {
        return dao.getAllListings().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addListing(listing: Listing) {
        dao.insertListing(listing.toEntity())
    }
}