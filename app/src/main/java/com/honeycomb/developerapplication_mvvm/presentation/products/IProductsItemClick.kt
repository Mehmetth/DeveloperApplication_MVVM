package com.honeycomb.developerapplication_mvvm.presentation.products

import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductResponse

interface IProductsItemClick {
    fun itemClicked(productEntity: ProductResponse)
}