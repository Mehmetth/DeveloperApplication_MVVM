package com.honeycomb.developerapplication_mvvm.presentation.productsdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.honeycomb.developerapplication_mvvm.data.product.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.*
import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductDetailResponse
import  com.honeycomb.developerapplication_mvvm.domain.common.result.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    private val _mProduct = MutableLiveData<Result<ProductDetailResponse>>()
    val mProduct = _mProduct

    fun fetchProductDetail(product_id: String) {
        viewModelScope.launch {
            productRepository.fetchProduct(product_id).collect {
                _mProduct.value = it
            }
        }
    }
}