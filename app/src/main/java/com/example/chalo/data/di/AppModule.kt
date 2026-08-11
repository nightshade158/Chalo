package com.example.chalo.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chalo.data.local.CartDao
import com.example.chalo.data.local.ChaloDatabase
import com.example.chalo.data.local.ListingDao
import com.example.chalo.data.repository.CartRepositoryImpl
import com.example.chalo.data.repository.ListingRepositoryImpl
import com.example.chalo.domain.repository.CartRepository
import com.example.chalo.domain.repository.ListingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ChaloDatabase {
        return Room.databaseBuilder(
            context,
            ChaloDatabase::class.java,
            "chalo_db"
        ).build()
    }
    @Provides
    fun provideListingDao(database: ChaloDatabase): ListingDao{
        return database.listingDao()
    }

    @Provides
    @Singleton
    fun provideListingRepository(dao: ListingDao): ListingRepository{
        return ListingRepositoryImpl(dao)
    }

    @Provides
    fun provideCartDao(database: ChaloDatabase): CartDao{
        return database.cartDao()
    }

    @Provides
    @Singleton
    fun provideCartRepository(dao: CartDao): CartRepository{
        return CartRepositoryImpl(dao)
    }
}