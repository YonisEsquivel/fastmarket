package com.example.fastmarket.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.fastmarket.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var registrobutton:Button
    lateinit var iniciobutton:Button
    lateinit var recuperarButton: TextView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth= Firebase.auth

        iniciobutton=findViewById(R.id.btningresarLogin)
        registrobutton=findViewById(R.id.btncrearLogin)
        recuperarButton=findViewById(R.id.recuperarpwLogin)

        val correo=findViewById<TextView>(R.id.emailLogin)
        val contrasena=findViewById<TextView>(R.id.contrasenaLogin)

        registrobutton.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
        iniciobutton.setOnClickListener {
            login(correo.text.toString(),contrasena.text.toString())
        }
        recuperarButton.setOnClickListener {
            startActivity(Intent(this, RecuperarActivity::class.java))
        }
    }

    private fun login(email:String, password:String){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext,user?.uid.toString(), Toast.LENGTH_SHORT).show()
                    val i=Intent(this, HomeActivity::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(baseContext,"Error. Vuelve a intentarlo", Toast.LENGTH_SHORT).show()
                }

            }
    }
}