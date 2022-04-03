package com.honeycomb.developerapplication_mvvm.data.product.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.honeycomb.developerapplication_mvvm.data.product.remote.dto.ProductResponse

@Database(entities = [ProductResponse::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}