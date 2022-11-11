package com.example.fastmarket.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fastmarket.R

class RegistroActivity : AppCompatActivity() {
    lateinit var registrobutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        registrobutton=findViewById(R.id.btnRegistro)
        registrobutton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}