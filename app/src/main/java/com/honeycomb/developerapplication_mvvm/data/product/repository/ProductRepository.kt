package com.honeycomb.developerapplication_mvvm.data.product.repository

import com.honeycomb.developerapplication_mvvm.data.product.ProductRemoteDataSource
import com.honeycomb.developerapplication_mvvm.data.product.local.ProductDao
import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductDetailResponse
import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductsResponse
import com.honeycomb.developerapplication_mvvm.domain.common.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productDao: ProductDao
) {

    suspend fun fetchAllProducts(): Flow<Result<ProductsResponse>?> {
        return flow {
            emit(fetchAllProductsCached())
            emit(Result.loading())
            val result = productRemoteDataSource.fetchAllProducts()

            if (result.status == Result.Status.SUCCESS) {
                result.data?.products?.let { it ->
                    productDao.deleteAll(it)
                    productDao.insertAll(it)
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchAllProductsCached(): Result<ProductsResponse>? =
        productDao.getAll()?.let {
            Result.success(ProductsResponse(it))
        }

    suspend fun fetchProduct(product_id: String): Flow<Result<ProductDetailResponse>> {
        return flow {
            emit(Result.loading())
            emit(productRemoteDataSource.fetchProduct(product_id))
        }.flowOn(Dispatchers.IO)
    }
}