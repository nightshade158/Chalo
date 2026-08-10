package com.example.chalo.presentation.listings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun AddListingDialog(
    onDismiss: () -> Unit,
    onConfirm: (String, String, Double, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("New Listing") },
        text = {
            Column {
                OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
                OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Price") })
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm(title, description, price.toDoubleOrNull() ?: 0.0, category)
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}