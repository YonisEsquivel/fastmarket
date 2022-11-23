package com.example.fastmarket.view.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.parser.IntegerParser
import com.example.fastmarket.R
import com.example.fastmarket.model.compras
import com.example.fastmarket.view.adapter.ComprasAdapter
import com.example.fastmarket.view.adapter.OnCompraItemClickListener
import com.example.fastmarket.viewmodel.ComprasViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlin.math.roundToInt

@Suppress("DEPRECATION")
class CarritoFragment : Fragment(), OnCompraItemClickListener {

    lateinit var firebaseAuth: FirebaseAuth
    val database:FirebaseFirestore=FirebaseFirestore.getInstance()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ComprasAdapter
    private val viewmodel by lazy {ViewModelProvider(this).get(ComprasViewModel::class.java)}
    lateinit var precioT:TextView
    lateinit var precioSub:TextView
    lateinit var precioIVA:TextView
    lateinit var bottoncompra:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth= Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_carrito, container, false)
        precioT=view.findViewById(R.id.precio_total)
        precioSub=view.findViewById(R.id.precio_subtotal)
        precioIVA=view.findViewById(R.id.precio_iva)
        bottoncompra=view.findViewById(R.id.boton_compra)
        recyclerView=view.findViewById(R.id.recyclerviewcompra)
        adapter= ComprasAdapter(requireContext(), this)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=adapter
        observedata()
        preciototal()
        bottoncompra.setOnClickListener {
            realizarcompra()
        }
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
        database.collection("compras")
            .document(mercado.producto)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(context, "Producto eliminado", Toast.LENGTH_SHORT).show()
            }

    }

    private fun preciototal(){
        database.collection("compras")
            .get()
            .addOnSuccessListener { result->
                val preciouni= mutableListOf<String>()
                for (document in result){
                    val precio=document["precio"].toString()
                    preciouni.add(precio!!)
                }
                val preciototal =preciouni.mapNotNull { it.toIntOrNull() }.sum()
                val preciosubtotal = ((preciototal/1.19)* 100.0).roundToInt()/100.0
                val precioiva = ((preciototal - preciosubtotal)* 100.0).roundToInt()/100.0
                precioT.setText("Total a pagar: $preciototal")
                precioSub.setText("Subtotal: $preciosubtotal")
                precioIVA.setText("IVA (19%): $precioiva")

            }
    }

    private fun realizarcompra(){
        val builder=AlertDialog.Builder(requireContext())
        builder.setTitle("FastMarket")
        builder.setMessage("¿Desea finalizar su compra?")
        builder.setPositiveButton("Aceptar"){
            dialog,which->
            findNavController().navigate(R.id.action_carritoFragment_to_homeFragment)
        }
        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

}