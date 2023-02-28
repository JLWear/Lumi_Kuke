import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lumi_kuke.R
import com.example.lumi_kuke.model.Result

//class RecipesAdapter(context: Context, private val results: List<Result>) : ArrayAdapter<Result>(context, 0, results) {
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//
//        val holder: ViewHolder = convertView?.tag as ViewHolder
//
//        val result = results[position]
//        holder.titleTextView.text = result.title
//
//        return convertView
//    }
//
//    private class ViewHolder {
//        lateinit var titleTextView: TextView
//    }
//}

class RecipesAdapter(private val recipes: List<com.example.lumi_kuke.model.Result>) :
    RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val imageView: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val result = recipes[position]
        holder.titleTextView.text = result.title
        Glide.with(holder.itemView.context)
            .load(result.image)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}

//
//import com.example.lumi_kuke.adapters.RecipeViewHolder
//import com.example.lumi_kuke.databinding.CardBinding
//import com.example.lumi_kuke.model.Recipe
//
//class RecipesAdapter(
//    private val recipes: List<Recipe>,
//    private val result: List<com.example.lumi_kuke.model.Result>
//)
//    : RecyclerView.Adapter<RecipeViewHolder>()
//{
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder
//    {
//        val from = LayoutInflater.from(parent.context)
//        val binding = CardBinding.inflate(from, parent, false)
//        return RecipeViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int)
//    {
//        holder.bindRecipes(result[position])
//    }
//
//    override fun getItemCount(): Int = recipes.size
//}