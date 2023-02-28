package com.example.lumi_kuke.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import com.example.lumi_kuke.R
import com.example.lumi_kuke.data.network.RecipesApi
import com.example.lumi_kuke.di.NetworkModule
import com.example.lumi_kuke.model.Recipe
import com.google.firebase.auth.FirebaseAuth
import okhttp3.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        run("https://api.spoonacular.com/recipes/complexSearch?apiKey=6d613aabfeb546e28a50b925bd121a07&query=")

        val networkModule = NetworkModule.provideApiService(RecipesApi::class.java)
        val call = networkModule.getRecipes()

//        val button = findViewById<Button>(R.id.button_request)
//        button.setOnClickListener {
//            call.enqueue(object : retrofit2.Callback<Recipe> {
//                override fun onResponse(call: Call<Recipe>, response: Response<Recipe>) {
//                    if (response.isSuccessful) {
//                        Log.e("success", response.body().toString())
//                    }
//                }
//
//                override fun onFailure(call: retrofit2.Call<Recipe>, t: Throwable) {
//                    t.printStackTrace()
//                    Log.e("error", t.message.toString())
//                }
//            })
//        }

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

//        val buttonClick = findViewById<SearchView>(R.id.searchView)
//        buttonClick.setOnClickListener {
//            client.newCall(request).enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {}
//                override fun onResponse(call: Call, response: Response) =
//                    println(response.body()?.string())
//            })
//        }

    }
}
