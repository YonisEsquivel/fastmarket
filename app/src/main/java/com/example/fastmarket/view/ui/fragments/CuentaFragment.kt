package com.example.fastmarket.view.ui.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.fastmarket.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class CuentaFragment : Fragment() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth= Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_cuenta, container, false)
        val btncamera=view.findViewById<Button>(R.id.photocamera)
        btncamera.setOnClickListener {
            val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }

        val btngaleria=view.findViewById<Button>(R.id.photogaleria)
        btngaleria.setOnClickListener {
            val intent=Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent, 456)
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView=view?.findViewById<ImageView>(R.id.photoperfil)
        if(requestCode==123){
            var bitmap=data?.extras?.get("data") as Bitmap
            imageView?.setImageBitmap(bitmap)
        }else if (requestCode==456){
            imageView?.setImageURI(data?.data)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm=view.findViewById<BottomNavigationView>(R.id.buttonNavigationMenu)
        btm.setOnNavigationItemReselectedListener {
                item ->
            when(item.itemId) {
                R.id.home_nav -> {
                    findNavController().navigate(R.id.action_cuentaFragment_to_homeFragment)
                }
                R.id.carrito_nav -> {
                    findNavController().navigate(R.id.action_cuentaFragment_to_carritoFragment)
                }
                R.id.comentarios_nav ->{
                    findNavController().navigate(R.id.action_cuentaFragment_to_comentarioFragment)
                }
                R.id.salir_bar ->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_cuentaFragment_to_loginActivity)
                    true
                }
                else -> false
            }
        }

    }
}