package com.example.chalo.presentation.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel()
) {
    val cartItems by viewModel.cartItems.collectAsState()
    var showCheckoutDialog by remember { mutableStateOf(false) }
    var showSuccessMessage by remember { mutableStateOf(false) }

    val context = LocalContext.current
    var pendingDate by remember { mutableStateOf("") }
    var pendingTime by remember { mutableStateOf("") }

    val paymentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.checkout(pendingDate, pendingTime)
            showSuccessMessage = true
        }
    }

    Scaffold(
        bottomBar = {
            if (cartItems.isNotEmpty()) {
                Button(
                    onClick = { showCheckoutDialog = true },
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Text("Proceed to Checkout")
                }
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (showSuccessMessage) {
                Text(
                    "Order placed successfully!",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                items(cartItems) { item ->
                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(item.title, style = MaterialTheme.typography.titleMedium)
                                Text("Qty: ${item.quantity}", style = MaterialTheme.typography.bodySmall)
                                Text("₹${item.price}", style = MaterialTheme.typography.bodyMedium)
                            }
                            TextButton(onClick = { viewModel.removeItem(item) }) {
                                Text("Remove")
                            }
                        }
                    }
                }
            }
        }
    }

    if (showCheckoutDialog) {
        CheckoutDialog(
            onDismiss = { showCheckoutDialog = false },
            onConfirm = { date, time ->
                pendingDate = date
                pendingTime = time
                showCheckoutDialog = false

                val totalAmount = cartItems.sumOf { it.price * it.quantity }
                val amountInPaise = (totalAmount * 100).toInt()

                val intent = Intent(context, com.example.chalo.presentation.cart.PaymentActivity::class.java)
                intent.putExtra("amount", amountInPaise)
                paymentLauncher.launch(intent)
            }
        )
    }

}