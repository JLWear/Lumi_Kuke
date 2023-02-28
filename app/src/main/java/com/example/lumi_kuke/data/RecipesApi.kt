package com.example.lumi_kuke.data

import com.example.lumi_kuke.model.Recipe
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RecipesApi {
    @GET("/recipes/complexSearch")
    fun getRecipes(@Query("query") query: String,
                   @Query("addRecipeInformation") addRecipeInformation: Boolean = true,
                   @Query("fillIngredients") fillIngredients: Boolean = true,
                   @Query("apiKey") apiKey: String): Call<Recipe>

    @GET("/recipes/random")
    fun getRandomRecipes(@Query("number") number: Number = 0,
                   @Query("apiKey") apiKey: String): Call<Recipe>
}