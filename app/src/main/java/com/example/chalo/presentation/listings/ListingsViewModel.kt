package com.example.chalo.presentation.listings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chalo.domain.model.Listing
import com.example.chalo.domain.repository.ListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingsViewModel @Inject constructor(
    private val repository: ListingRepository
) : ViewModel() {

    val listings: StateFlow<List<Listing>> = repository.getAllListings()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addListing(title: String, description: String, price: Double, category: String){
        viewModelScope.launch {
            repository.addListing(
                Listing(
                    id = 0,
                    title = title,
                    description = description,
                    price = price,
                    category = category
                )
            )
        }
    }
}