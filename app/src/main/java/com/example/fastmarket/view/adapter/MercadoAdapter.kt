package com.example.fastmarket.view.adapter

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fastmarket.R
import com.example.fastmarket.model.mercado
import com.squareup.picasso.Picasso

class MercadoAdapter(private val context:Context, var clickListener: OnMercadoItemClickListener):RecyclerView.Adapter<MercadoAdapter.ViewHolder>() {
    private var mercadolista= mutableListOf<mercado>()

    fun setListData(data: MutableList<mercado>){
        mercadolista=data
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int):ViewHolder {
        val v=LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_mercado,
        ViewGroup, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun binWew(mercado: mercado, action: OnMercadoItemClickListener){
            itemView.findViewById<TextView>(R.id.productoMercado).text=mercado.producto
            itemView.findViewById<TextView>(R.id.detallesMercado).text=mercado.detalle
            itemView.findViewById<TextView>(R.id.precioMercado).text=mercado.precio
            Picasso.with(context).load(mercado.image).into(itemView.findViewById<ImageView>(R.id.imgMercado))
            val btncarrito =itemView.findViewById<ImageButton>(R.id.add_cart)

            btncarrito.setOnClickListener{
                action.OnItemClick(mercado,adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var mercado=mercadolista[i]
        viewHolder.binWew(mercado, clickListener)
    }

    override fun getItemCount(): Int {
        return if (mercadolista.size>0){
            mercadolista.size
        }else{
            0
        }
    }

}

interface OnMercadoItemClickListener{
    fun OnItemClick(mercado:mercado, position:Int)
}