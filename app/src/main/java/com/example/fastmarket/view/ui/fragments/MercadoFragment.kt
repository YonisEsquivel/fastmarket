package com.example.fastmarket.view.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fastmarket.R
import com.example.fastmarket.model.mercado
import com.example.fastmarket.view.adapter.MercadoAdapter
import com.example.fastmarket.view.adapter.OnMercadoItemClickListener
import com.example.fastmarket.viewmodel.MarketViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.text.ParsePosition

@Suppress("DEPRECATION")
class MercadoFragment : Fragment(), OnMercadoItemClickListener {
    var database:FirebaseFirestore=FirebaseFirestore.getInstance()
    lateinit var recyclerMercado: RecyclerView
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var adapter: MercadoAdapter
    private val viewmodel by lazy {ViewModelProvider(this).get(MarketViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth=Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_mercado, container, false)
        recyclerMercado=view.findViewById(R.id.recyclerview)
        adapter = MercadoAdapter(requireContext(), this)
        recyclerMercado.layoutManager=LinearLayoutManager(context)
        recyclerMercado.adapter=adapter
        observeData()
        return view
    }

    fun observeData(){
        viewmodel.mercadoData().observe(viewLifecycleOwner, Observer {
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
                    findNavController().navigate(R.id.action_mercadoFragment_to_homeFragment)
                }
                R.id.carrito_nav -> {
                    findNavController().navigate(R.id.action_mercadoFragment_to_carritoFragment)
                }
                R.id.comentarios_nav ->{
                    findNavController().navigate(R.id.action_mercadoFragment_to_comentarioFragment)
                }
                R.id.salir_bar ->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_mercadoFragment_to_loginActivity)
                    true
                }
                else -> false
            }
        }

    }

    override fun OnItemClick(mercado: mercado, position: Int) {
        val producto:String=mercado.producto
        val detalle:String=mercado.detalle
        val precio:String=mercado.precio
        val image:String=mercado.image
        val dato= hashMapOf(
            "producto" to producto,
            "detalle" to detalle,
            "precio" to precio,
            "image" to image
        )
        database.collection("compras")
            .document(producto)
            .set(dato)
            .addOnSuccessListener {
                Toast.makeText(context, "Añadido al carrito", Toast.LENGTH_SHORT).show()
            }
    }

    override fun OnDeseosClick(mercado: mercado, position: Int) {

    }


}