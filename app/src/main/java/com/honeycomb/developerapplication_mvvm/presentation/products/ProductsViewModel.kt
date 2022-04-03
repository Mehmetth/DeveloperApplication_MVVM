package com.honeycomb.developerapplication_mvvm.presentation.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductsResponse
import com.honeycomb.developerapplication_mvvm.data.product.repository.ProductRepository
import  com.honeycomb.developerapplication_mvvm.domain.common.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    private val _movieList = MutableLiveData<Result<ProductsResponse>>()
    val movieList = _movieList

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            productRepository.fetchAllProducts().collect {
                _movieList.value = it
            }
        }
    }
}