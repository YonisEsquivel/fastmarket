package com.example.fastmarket.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fastmarket.R
import com.example.fastmarket.model.compras
import com.squareup.picasso.Picasso

class ComprasAdapter(private val context:Context, var clickListener: OnCompraItemClickListener):RecyclerView.Adapter<ComprasAdapter.ViewHolder>() {
    private var mercadolista= mutableListOf<compras>()

    fun setListData(data: MutableList<compras>){
        mercadolista=data
    }

    override fun onCreateViewHolder(ViewGroup: ViewGroup, i: Int):ViewHolder {
        val v=LayoutInflater.from(ViewGroup.context).inflate(R.layout.card_view_compras,
            ViewGroup, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun binWew(mercado: compras, action: OnCompraItemClickListener){
            itemView.findViewById<TextView>(R.id.productoMercado).text=mercado.producto
            itemView.findViewById<TextView>(R.id.detallesMercado).text=mercado.detalle
            itemView.findViewById<TextView>(R.id.precioMercado).text=mercado.precio
            Picasso.with(context).load(mercado.image).into(itemView.findViewById<ImageView>(R.id.imgMercado))
            val btneliminar_cart =itemView.findViewById<ImageButton>(R.id.eliminar_cart)
            btneliminar_cart.setOnClickListener{
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

interface OnCompraItemClickListener{
    fun OnItemClick(mercado:compras, position:Int)
}