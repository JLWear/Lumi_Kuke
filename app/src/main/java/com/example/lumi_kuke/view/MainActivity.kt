package com.example.lumi_kuke.view

import RecipesAdapter
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lumi_kuke.R
import com.example.lumi_kuke.data.RecipesApi
import com.example.lumi_kuke.databinding.ActivityMainBinding
import com.example.lumi_kuke.di.NetworkModule
import com.example.lumi_kuke.model.Recipe
import com.example.lumi_kuke.util.Constants.Companion.API_KEY
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private lateinit var listView: ListView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArrayAdapter<com.example.lumi_kuke.model.Result>
    var layoutManager: LinearLayoutManager? = null

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        run("https://api.spoonacular.com/recipes/complexSearch?apiKey=6d613aabfeb546e28a50b925bd121a07&query=")

        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(this)
        //listView = findViewById(R.id.list)
        recyclerView = findViewById(R.id.recycler)

//        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList())
//        listView.adapter = adapter

//        val mainActivity = this@MainActivity
//        binding.recyclerView.apply {
//            layoutManager = GridLayoutManager(applicationContext, 1)
//            recyclerView.adapter = RecipesAdapter(result = )
//        }


        val networkModule = NetworkModule.provideApiService(RecipesApi::class.java)
        val call = networkModule.getRandomRecipes( 10, API_KEY)
        call.enqueue(object : retrofit2.Callback<Recipe> {
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                if (response.isSuccessful) {
                    Log.e("success", response.body().toString())
                }
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }
        })

    }

    override fun onQueryTextSubmit(query: String): Boolean {
        val networkModule = NetworkModule.provideApiService(RecipesApi::class.java)
        val call = networkModule.getRecipes(query, true, true, API_KEY)
        call.enqueue(object : retrofit2.Callback<Recipe> {
            override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
                if (response.isSuccessful) {
                    Log.e("success", response.body().toString())
//                    val results = response.body()?.results ?: emptyList()
//                    adapter.clear()
//                    adapter.addAll(results)
//                    adapter.notifyDataSetChanged()
                    query.let {
                        CoroutineScope(Dispatchers.IO).launch {
                            withContext(Dispatchers.Main) {
                                recyclerView.adapter = RecipesAdapter(response.body()?.results!!.map { result ->
                                    com.example.lumi_kuke.model.Result(
                                        title = result.title,
                                        image = result.image,
                                        id = result.id,
                                        cookingMinutes = result.cookingMinutes,
                                        extendedIngredients = result.extendedIngredients,
                                        sourceUrl = result.sourceUrl,
                                        summary = result.summary,
                                        vegan = result.vegan,
                                        vegetarian = result.vegetarian,
                                    )
                                })
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Recipe>, t: Throwable) {
                t.printStackTrace()
                Log.e("error", t.message.toString())
            }
        })
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater;
        inflater.inflate(R.menu.logout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            firebaseAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()
    }
}
