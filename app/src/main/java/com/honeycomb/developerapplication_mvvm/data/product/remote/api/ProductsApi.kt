package com.honeycomb.developerapplication_mvvm.data.product.remote.api

import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductDetailResponse
import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductsApi {
    @GET("cart/list")
    suspend fun getProducts() : Response<ProductsResponse>

    @GET("cart/{product_id}/detail")
    suspend fun getProductDetail(@Path("product_id") product_id: String) : Response<ProductDetailResponse>
}