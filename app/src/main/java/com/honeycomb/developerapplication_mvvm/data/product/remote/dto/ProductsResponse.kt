package com.honeycomb.developerapplication_mvvm.data.product.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ProductsResponse(
    @SerializedName("products")
    var products: List<ProductResponse>
): Parcelable