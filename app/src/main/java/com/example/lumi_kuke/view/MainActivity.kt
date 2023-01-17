package com.example.lumi_kuke.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import com.example.lumi_kuke.R
import com.google.firebase.auth.FirebaseAuth
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        run("https://api.spoonacular.com/recipes/complexSearch?apiKey=6d613aabfeb546e28a50b925bd121a07&query=")

        val buttonClick = findViewById<Button>(R.id.button_logout)
        buttonClick.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        val buttonClick = findViewById<SearchView>(R.id.searchView)
        buttonClick.setOnClickListener {
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                override fun onResponse(call: Call, response: Response) =
                    println(response.body()?.string())
            })
        }

    }
}
