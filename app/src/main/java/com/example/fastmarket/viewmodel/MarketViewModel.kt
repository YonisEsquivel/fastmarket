package com.example.fastmarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fastmarket.model.mercado
import com.example.fastmarket.repositorio.repo

class MarketViewModel: ViewModel() {
    val repo=repo()

    fun mercadoData():LiveData<MutableList<mercado>>{
        val mutabledata=MutableLiveData<MutableList<mercado>>()
        repo.getProductData().observeForever{result ->
            mutabledata.value=result
        }
        return mutabledata
    }
}