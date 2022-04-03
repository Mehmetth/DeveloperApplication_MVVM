package com.honeycomb.developerapplication_mvvm.data.product

import android.content.res.Resources
import android.provider.Settings.Global.getString
import com.honeycomb.developerapplication_mvvm.R
import com.honeycomb.developerapplication_mvvm.data.product.remote.api.ProductsApi
import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductDetailResponse
import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductsResponse
import com.honeycomb.developerapplication_mvvm.domain.common.error.ErrorUtils
import com.honeycomb.developerapplication_mvvm.domain.common.result.Result
import com.honeycomb.developerapplication_mvvm.utils.AndroidUtils
import retrofit2.Retrofit
import retrofit2.Response
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchAllProducts(): Result<ProductsResponse> {
        val productsApi = retrofit.create(ProductsApi::class.java);
        return getResponse(
            request = { productsApi.getProducts() },
            defaultErrorMessage = AndroidUtils.globalContext.getString(R.string.error_products))

    }

    suspend fun fetchProduct(product_id: String): Result<ProductDetailResponse> {
        val productsApi = retrofit.create(ProductsApi::class.java);
        return getResponse(
            request = { productsApi.getProductDetail(product_id) },
            defaultErrorMessage = AndroidUtils.globalContext.getString(R.string.error_product_detail))
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error(e.toString(), null)
        }
    }
}