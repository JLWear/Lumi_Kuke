package com.example.lumi_kuke.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("cookingMinutes")
    val cookingMinutes: Int,
    @SerializedName("sourceUrl")
    val sourceUrl: String,
    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient>,
    @SerializedName("vegan")
    val vegan: Boolean,
    @SerializedName("vegetarian")
    val vegetarian: Boolean,
    @SerializedName("summary")
    val summary: String,
)