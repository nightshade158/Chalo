package com.example.chalo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chalo.presentation.listings.ListingsScreen
import com.example.chalo.presentation.listings.ListingDetailScreen

@Composable
fun ChaloNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.LISTINGS
    ) {
        composable(NavRoutes.LISTINGS) {
            ListingsScreen(
                onListingClick = { listingId ->
                    navController.navigate(NavRoutes.listingDetail(listingId))
                }
            )
        }

        composable(
            route = NavRoutes.LISTING_DETAIL,
            arguments = listOf(navArgument("listingId") { type = NavType.IntType })
        ) { backStackEntry ->
            val listingId = backStackEntry.arguments?.getInt("listingId") ?: 0
            ListingDetailScreen(
                listingId = listingId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}