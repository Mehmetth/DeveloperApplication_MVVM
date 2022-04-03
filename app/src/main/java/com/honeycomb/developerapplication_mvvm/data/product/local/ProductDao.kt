package com.honeycomb.developerapplication_mvvm.data.product.local

import androidx.room.*
import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductResponse

@Dao
interface  ProductDao {

    @Query("SELECT * FROM productResponse")
    fun getAll(): List<ProductResponse>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<ProductResponse>)

    @Delete
    fun delete(product: ProductResponse)

    @Delete
    fun deleteAll(product: List<ProductResponse>)
}