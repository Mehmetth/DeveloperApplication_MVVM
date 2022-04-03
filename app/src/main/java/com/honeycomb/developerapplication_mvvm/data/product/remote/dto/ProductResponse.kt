package com.honeycomb.developerapplication_mvvm.data.product.remote.dto

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class ProductResponse(
    @NonNull
    @PrimaryKey
    var product_id: String,
    var name: String,
    var price: Int,
    var image: String
): Parcelable