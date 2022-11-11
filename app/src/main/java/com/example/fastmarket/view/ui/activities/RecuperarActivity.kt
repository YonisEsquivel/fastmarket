package com.example.fastmarket.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fastmarket.R

class RecuperarActivity : AppCompatActivity() {
    lateinit var restaurarbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar)

        restaurarbutton=findViewById(R.id.btnRecuperar)
        restaurarbutton.setOnClickListener {
            startActivity((Intent(this, LoginActivity::class.java)))
        }
    }
}