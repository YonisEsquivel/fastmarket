package com.example.fastmarket.repositorio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fastmarket.model.mercado
import com.google.firebase.firestore.FirebaseFirestore

class repo {
    fun getProductData():LiveData<MutableList<mercado>>{
        val mutabledata=MutableLiveData<MutableList<mercado>>()

        FirebaseFirestore.getInstance().collection("productos").get()
            .addOnSuccessListener {result->
                val listData= mutableListOf<mercado>()
                for (document in result){
                    val producto=document.getString("producto")
                    val detalle=document.getString("detalle")
                    val precio=document.getString("precio")
                    val image=document.getString("image")
                    val mercados=mercado(producto!!, detalle!!, precio!!, image!!)
                    listData.add(mercados)
                }
                mutabledata.value=listData
        }
        return mutabledata
    }
}