package com.example.chalo.presentation.navigation

object NavRoutes{
    const val LISTINGS = "listings"
    const val SEARCH = "search"
    const val CART = "cart"
    const val ORDERS = "orders"
    const val PROFILE = "profile"
    const val LISTING_DETAIL = "listing_detail/{listingId}"

    fun listingDetail(listingId: Int) = "listing_detail/$listingId"
}