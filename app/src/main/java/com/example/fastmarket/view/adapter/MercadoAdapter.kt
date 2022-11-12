package com.example.fastmarket.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fastmarket.R

class MercadoAdapter:RecyclerView.Adapter<MercadoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int):ViewHolder {
        val v=LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_mercado,
        ViewGroup, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrecio: TextView
        var itemDetalle: TextView

        init {
            itemImage = itemView.findViewById(R.id.imgMercado)
            itemTitle = itemView.findViewById(R.id.titleMercado)
            itemDetalle = itemView.findViewById(R.id.detallesMercado)
            itemPrecio = itemView.findViewById(R.id.precioMercado)
        }
    }

    val tittles= arrayOf("Harrina Pan", "Café Aguila Roja", "Queso Colanta",
    "Aceite Frescampo", "Refresco Hit")
    val detalles = arrayOf("1000 gr", "500 gr", "400 gr", "3000 ml", "500 ml")
    val precios= arrayOf("$4.800", "$16.750", "$8.090", "$19.000", "$19.984", "$1.792")
    val images= arrayOf(R.drawable.p_harinapan, R.drawable.p_cafe, R.drawable.p_queso,
    R.drawable.p_aceite, R.drawable.p_hit)

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text=tittles[i]
        viewHolder.itemDetalle.text=detalles[i]
        viewHolder.itemPrecio.text=precios[i]
        viewHolder.itemImage.setImageResource(images[i])
    }

    override fun getItemCount(): Int {
        return tittles.size
    }




}