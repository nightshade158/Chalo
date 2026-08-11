package com.example.chalo.presentation.navigation

object NavRoutes{
    const val LISTINGS = "listings"
    const val LISTING_DETAIL = "listing_detail/{listingId}"

    fun listingDetail(listingId: Int) = "listing_detail/$listingId"
}