package com.example.fastmarket.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fastmarket.R
import com.example.fastmarket.model.compras
import com.example.fastmarket.view.adapter.ComprasAdapter
import com.example.fastmarket.view.adapter.OnCompraItemClickListener
import com.example.fastmarket.viewmodel.ComprasViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class CarritoFragment : Fragment(), OnCompraItemClickListener {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ComprasAdapter
    private val viewmodel by lazy {ViewModelProvider(this).get(ComprasViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth= Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_carrito, container, false)
        recyclerView=view.findViewById(R.id.recyclerviewcompra)
        adapter= ComprasAdapter(requireContext(), this)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=adapter
        observedata()
        return view
    }

    private fun observedata() {
        viewmodel.fetchComprasData().observe(viewLifecycleOwner, Observer{
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm=view.findViewById<BottomNavigationView>(R.id.buttonNavigationMenu)
        btm.setOnNavigationItemReselectedListener {
                item ->
            when(item.itemId) {
                R.id.home_nav -> {
                    findNavController().navigate(R.id.action_carritoFragment_to_homeFragment)
                }
                R.id.comentarios_nav ->{
                    findNavController().navigate(R.id.action_carritoFragment_to_comentarioFragment)
                }
                R.id.salir_bar ->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_carritoFragment_to_loginActivity)
                    true
                }
                else -> false
            }
        }

    }

    override fun OnItemClick(mercado: compras, position: Int) {
        TODO("Not yet implemented")
    }

}