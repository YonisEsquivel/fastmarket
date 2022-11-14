package com.example.fastmarket.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fastmarket.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecuperarActivity : AppCompatActivity() {
    lateinit var restaurarbutton: Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar)
        firebaseAuth= Firebase.auth

        val email=findViewById<EditText>(R.id.emailRecuperar)
        restaurarbutton=findViewById(R.id.btnRecuperar)
        restaurarbutton.setOnClickListener {
            restaurarpassword(email.text.toString())
        }
    }

    private fun restaurarpassword(email:String){
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this){
                task->if (task.isSuccessful){
                    Toast.makeText(baseContext, "Correo de cambio de contraseña enviado",
                    Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }else{
                    Toast.makeText(baseContext, "Error, correo no existe",
                    Toast.LENGTH_SHORT).show()
                }
            }
    }
}