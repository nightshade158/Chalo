package com.example.chalo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ListingEntity::class], version = 1)
abstract class ChaloDatabase : RoomDatabase() {
    abstract fun listingDao(): ListingDao
}