import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lumi_kuke.R

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