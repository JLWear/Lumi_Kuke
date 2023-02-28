package com.example.lumi_kuke.data

import com.example.lumi_kuke.data.network.RecipesApi
import com.example.lumi_kuke.model.Recipe
import retrofit2.Call
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val recipesApi: RecipesApi) {
    fun getRecipes(): Call<Recipe> {
        return recipesApi.getRecipes()
    }
}