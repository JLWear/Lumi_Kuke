package com.example.lumi_kuke.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.lumi_kuke.databinding.CardBinding
import com.example.lumi_kuke.model.Recipe

class RecipeViewHolder (private val recipesBinding: CardBinding) : RecyclerView.ViewHolder(recipesBinding.root) {
    fun bindRecipes(recipe: com.example.lumi_kuke.model.Result) {
        //recipesBinding.image.setImageResource(recipe.image)
        recipesBinding.title.text = recipe.title
    }
}