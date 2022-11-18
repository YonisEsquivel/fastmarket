package com.example.fastmarket.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fastmarket.model.compras
import com.example.fastmarket.repositorio.repo

class ComprasViewModel: ViewModel() {
    val repo= repo()

    fun fetchComprasData(): LiveData<MutableList<compras>> {
        val mutabledata= MutableLiveData<MutableList<compras>>()
        repo.getComprasData().observeForever{
            mutabledata.value=it
        }
        return mutabledata
    }
}