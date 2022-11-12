package com.example.fastmarket.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import com.example.fastmarket.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardmercado=view.findViewById<ImageView>(R.id.cardMenumercado)
        cardmercado.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mercadoFragment)
        }

        val cardcarrito=view.findViewById<ImageView>(R.id.cardMenucarrito)
        cardcarrito.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_carritoFragment)
        }

        val cardcuenta=view.findViewById<ImageView>(R.id.cardMenucuenta)
        cardcuenta.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cuentaFragment)
        }

        val carddeseos=view.findViewById<ImageView>(R.id.cardMenudeseo)
        carddeseos.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_deseosFragment)
        }

        val cardconfig=view.findViewById<ImageView>(R.id.cardMenuconfig)
        cardconfig.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_configFragment)
        }

        val cardruta=view.findViewById<ImageView>(R.id.cardMenuruta)
        cardruta.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_rutaFragment)
        }


    }

}