package com.example.fastmarket.view.ui.fragments

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.fastmarket.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.type.LatLng

@Suppress("DEPRECATION")
class RutaFragment : Fragment(), OnMapReadyCallback {

    lateinit var firebaseAuth: FirebaseAuth

    private lateinit var googlemap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth= Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ruta, container, false)
        val mapFragment = this.childFragmentManager.findFragmentById(R.id.map_viewRuta)as SupportMapFragment
        mapFragment.getMapAsync(this)
        return view
    }

    override fun onMapReady(map: GoogleMap) {
        val colombia=com.google.android.gms.maps.model.LatLng(4.570868, -74.29733)
        map?.let {
            this.googlemap=it
            map.addMarker(MarkerOptions().position(colombia))
        }
        enableLocation()
    }

    private fun isLocationPermissionGrated()=ContextCompat.checkSelfPermission(
        this.requireContext(),
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )==PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
    private fun enableLocation(){
        if(!::googlemap.isInitialized)return
        if (isLocationPermissionGrated()){
            googlemap.isMyLocationEnabled=true
        }else{
            requestLocationPermission()
        }
    }

    companion object{
        const val REQUEST_CODE_LOCATION=0
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this.requireActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION
        )){
            Toast.makeText(this.context,
            "Active los permisos en Ajustes",Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this.requireActivity(),
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                com.example.fastmarket.view.ui.fragments.RutaFragment.Companion.REQUEST_CODE_LOCATION
            )
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            com.example.fastmarket.view.ui.fragments.RutaFragment.Companion.REQUEST_CODE_LOCATION
            -> if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                googlemap.isMyLocationEnabled=true
            }else{
                Toast.makeText(this.context,
                    "Para activar la localización ve a ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT).show()
            }else ->{}
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btm=view.findViewById<BottomNavigationView>(R.id.buttonNavigationMenu)


        btm.setOnNavigationItemReselectedListener {
                item ->
            when(item.itemId) {
                R.id.home_nav -> {
                    findNavController().navigate(R.id.action_rutaFragment_to_homeFragment)
                }
                R.id.carrito_nav -> {
                    findNavController().navigate(R.id.action_rutaFragment_to_carritoFragment)
                }
                R.id.comentarios_nav ->{
                    findNavController().navigate(R.id.action_rutaFragment_to_comentarioFragment)
                }
                R.id.salir_bar ->{
                    firebaseAuth.signOut()
                    findNavController().navigate(R.id.action_rutaFragment_to_loginActivity)
                    true
                }
                else -> false
            }
        }
    }

}