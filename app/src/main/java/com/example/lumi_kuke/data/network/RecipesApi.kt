package com.example.lumi_kuke.data.network

import com.example.lumi_kuke.model.Recipe
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RecipesApi {
    @GET("/recipes/complexSearch?apiKey=6d613aabfeb546e28a50b925bd121a07&query=pate&addRecipeInformation=true&fillIngredients=true")
    fun getRecipes(): Call<Recipe>
}