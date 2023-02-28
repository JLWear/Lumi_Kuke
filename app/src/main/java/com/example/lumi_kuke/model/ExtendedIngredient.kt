package com.example.lumi_kuke.model


import com.google.gson.annotations.SerializedName

data class ExtendedIngredient(
    @SerializedName("name")
    val name: String,
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("unit")
    val unit: String
)