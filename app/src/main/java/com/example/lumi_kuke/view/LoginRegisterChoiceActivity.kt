package com.example.lumi_kuke.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.lumi_kuke.R
import com.google.firebase.auth.FirebaseAuth


class LoginRegisterChoiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginregisterchoice)
        supportActionBar?.hide();

        val buttonRegister = findViewById<Button>(R.id.button_register)
        buttonRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        val buttonLogin = findViewById<Button>(R.id.button_login)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}