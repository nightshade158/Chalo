package com.example.chalo.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ListingDao{
    @Query("SELECT * FROM listings")
    fun getAllListings(): Flow<List<ListingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListing(listing: ListingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listings: List<ListingEntity>)
}