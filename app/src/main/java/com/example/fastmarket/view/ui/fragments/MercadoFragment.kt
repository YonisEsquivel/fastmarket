package com.example.fastmarket.view.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fastmarket.R
import com.example.fastmarket.view.adapter.MercadoAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class MercadoFragment : Fragment() {
    lateinit var recyclerMercado: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_mercado, container, false)
        recyclerMercado=view.findViewById(R.id.recyclerview)
        val adapter = MercadoAdapter()
        recyclerMercado.layoutManager=LinearLayoutManager(context)
        recyclerMercado.adapter=adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm=view.findViewById<BottomNavigationView>(R.id.buttonNavigationMenu)
        btm.setOnNavigationItemReselectedListener {
            item ->
            when(item.itemId) {
                R.id.home_nav -> {
                    findNavController().navigate(R.id.action_mercadoFragment_to_homeFragment)
                }
                R.id.carrito_nav -> {
                    findNavController().navigate(R.id.action_mercadoFragment_to_carritoFragment)
                }
                R.id.comentarios_nav ->{
                    findNavController().navigate(R.id.action_mercadoFragment_to_comentarioFragment)
                }
                R.id.config_bar ->{
                    findNavController().navigate(R.id.action_mercadoFragment_to_configFragment)
                }
                else -> false
            }
        }

    }


}