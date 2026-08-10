package com.example.chalo.presentation.listings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ListingsScreen(
    viewModel: ListingsViewModel = hiltViewModel()
) {
    val listings by viewModel.listings.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(listings) { listing ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(listing.title, style = MaterialTheme.typography.titleMedium)
                        Text(listing.category, style = MaterialTheme.typography.bodySmall)
                        Text("₹${listing.price}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }

    if (showDialog) {
        AddListingDialog(
            onDismiss = { showDialog = false },
            onConfirm = { title, desc, price, category ->
                viewModel.addListing(title, desc, price, category)
                showDialog = false
            }
        )
    }
}