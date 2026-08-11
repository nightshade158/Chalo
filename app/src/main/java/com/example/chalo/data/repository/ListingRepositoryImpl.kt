package com.example.chalo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
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
    override fun getListingsPaged(): Flow<PagingData<Listing>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { dao.getListingsPaged() }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun addListing(listing: Listing) {
        dao.insertListing(listing.toEntity())
    }
}