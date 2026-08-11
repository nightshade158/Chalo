package com.example.chalo.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(NavRoutes.LISTINGS, "HOME", Icons.Default.Home),
    BottomNavItem(NavRoutes.SEARCH, "SEARCH", Icons.Default.Search),
    BottomNavItem(NavRoutes.CART, "CART", Icons.Default.ShoppingCart),
    BottomNavItem(NavRoutes.ORDERS, "ORDERS", Icons.AutoMirrored.Filled.List),
    BottomNavItem(NavRoutes.PROFILE, "PROFILE", Icons.Default.Person)


)