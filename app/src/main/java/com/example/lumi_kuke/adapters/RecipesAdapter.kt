//package com.example.lumi_kuke.adapters
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.lumi_kuke.databinding.ActivityMainBinding
//import com.example.lumi_kuke.model.Recipe
//import com.example.lumi_kuke.model.Result
//
//class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.CardViewHolder>() {
//
//    private var recipes = emptyList<Result>()
//
//    class CardViewHolder(private val binding: ActivityMainBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(result: Result) {
//            binding.result = result
//            binding.executePendingBindings()
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): CardViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = ActivityMainBinding.inflate(layoutInflater, parent, false)
//                return CardViewHolder(binding)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
//        return CardViewHolder.from(parent)
//    }
//
//    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
//        val currentRecipe = recipes[position]
//        holder.bind(currentRecipe)
//    }
//
//    override fun getItemCount(): Int {
//        return recipes.size
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setData(newData: Recipe) {
//        recipes = newData.results
//        notifyDataSetChanged()
//    }
//}