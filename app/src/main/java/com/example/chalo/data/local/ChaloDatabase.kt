package com.example.chalo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ListingEntity::class, CartItemEntity::class], version = 2)
abstract class ChaloDatabase : RoomDatabase() {
    abstract fun listingDao(): ListingDao
    abstract fun cartDao(): CartDao
}