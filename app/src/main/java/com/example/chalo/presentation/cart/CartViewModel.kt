package com.example.chalo.presentation.cart

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.chalo.data.worker.BookingReminderWorker
import com.example.chalo.domain.model.CartItem
import com.example.chalo.domain.repository.CartRepository
import com.example.chalo.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository,
    private val orderRepository: OrderRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val cartItems: StateFlow<List<CartItem>> = repository.getCartItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun removeItem(item: CartItem) {
        viewModelScope.launch {
            repository.removeFromCart(item)
        }
    }

    fun addToCart(listingId: Int, title: String, price: Double) {
        viewModelScope.launch {
            repository.addToCart(listingId, title, price)
        }
    }

    fun checkout(date: String, time: String) {
        viewModelScope.launch {
            val items = cartItems.value
            orderRepository.checkout(items, date, time)
            scheduleReminders(items)
        }
    }

    private fun scheduleReminders(items: List<CartItem>) {
        for (item in items) {
            val data = Data.Builder()
                .putString("listingTitle", item.title)
                .build()

            val request = OneTimeWorkRequestBuilder<BookingReminderWorker>()
                .setInitialDelay(1, TimeUnit.MINUTES)
                .setInputData(data)
                .build()

            WorkManager.getInstance(context).enqueue(request)
        }
    }
}