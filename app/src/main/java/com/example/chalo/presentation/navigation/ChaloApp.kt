package com.example.chalo.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chalo.presentation.cart.CartScreen
import com.example.chalo.presentation.listings.ListingDetailScreen
import com.example.chalo.presentation.listings.ListingsScreen
import com.example.chalo.presentation.orders.OrdersScreen
import com.example.chalo.presentation.profile.ProfileScreen
import com.example.chalo.presentation.search.SearchScreen

@Composable
fun ChaloScaffold() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.LISTINGS,
            modifier = androidx.compose.ui.Modifier.padding(padding)
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
            composable(NavRoutes.SEARCH) { SearchScreen() }
            composable(NavRoutes.CART) { CartScreen() }
            composable(NavRoutes.ORDERS) { OrdersScreen() }
            composable(NavRoutes.PROFILE) { ProfileScreen() }
        }
    }
}