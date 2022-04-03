package com.honeycomb.developerapplication_mvvm.data.product.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ProductDetailResponse(
    @SerializedName("product_id")
    var product_id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("price")
    var price: Int,
    @SerializedName("image")
    var image: String,
    @SerializedName("description")
    var description: String
): Parcelable