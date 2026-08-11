package com.example.chalo.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chalo.domain.model.CartItem
import com.example.chalo.domain.repository.CartRepository
import com.example.chalo.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    val cartItems: StateFlow<List<CartItem>> = repository.getCartItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    fun removeItem(item: CartItem){
        viewModelScope.launch {
            repository.removeFromCart(item)
        }
    }
    fun addToCart(listingId: Int, title: String, price: Double) {
        viewModelScope.launch {
            repository.addToCart(listingId, title, price)
        }
    }
    fun checkout(date: String, time: String){
        viewModelScope.launch {
            orderRepository.checkout(cartItems.value, date, time)
        }
    }
}