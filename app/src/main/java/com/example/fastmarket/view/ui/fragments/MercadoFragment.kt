package com.example.fastmarket.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fastmarket.R
import com.example.fastmarket.view.adapter.MercadoAdapter

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
}