package com.example.fastmarket.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.fastmarket.R

class LoginActivity : AppCompatActivity() {
    lateinit var registrobutton:Button
    lateinit var iniciobutton:Button
    lateinit var recuperarButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        iniciobutton=findViewById(R.id.btningresarLogin)
        registrobutton=findViewById(R.id.btncrearLogin)
        recuperarButton=findViewById(R.id.recuperarpwLogin)

        registrobutton.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
        iniciobutton.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        recuperarButton.setOnClickListener {
            startActivity(Intent(this, RecuperarActivity::class.java))
        }
    }
}