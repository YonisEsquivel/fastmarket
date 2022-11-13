package com.example.fastmarket.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fastmarket.R
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class ConfigFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_config, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm=view.findViewById<BottomNavigationView>(R.id.buttonNavigationMenu)
        btm.setOnNavigationItemReselectedListener {
                item ->
            when(item.itemId) {
                R.id.home_nav -> {
                    findNavController().navigate(R.id.action_configFragment_to_homeFragment)
                }
                R.id.carrito_nav -> {
                    findNavController().navigate(R.id.action_configFragment_to_carritoFragment)
                }
                R.id.comentarios_nav ->{
                    findNavController().navigate(R.id.action_configFragment_to_comentarioFragment)
                }
                else -> false
            }
        }

    }
}