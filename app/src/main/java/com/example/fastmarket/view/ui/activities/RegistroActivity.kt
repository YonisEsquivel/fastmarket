package com.example.fastmarket.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fastmarket.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistroActivity : AppCompatActivity() {
    lateinit var registrobutton: Button
    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        firebaseAuth= Firebase.auth

        registrobutton=findViewById(R.id.btnRegistro)
        val correo=findViewById<EditText>(R.id.emailRegistro)
        val contrasena=findViewById<EditText>(R.id.contrasenaRegistro)

        registrobutton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            crearcuenta(correo.text.toString(), contrasena.text.toString())
        }
    }

    private fun crearcuenta(email:String, password:String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { Task ->
                if (Task.isSuccessful) {
                    Toast.makeText(baseContext, "Cuenta Creada con Éxito", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    Toast.makeText(baseContext, "Error. Vuelve a Intentarlo", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}