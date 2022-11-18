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

interface OnMercadoItemClickListener{
    fun OnItemClick(mercado:mercado, position:Int)
    fun OnDeseosClick(mercado:mercado, position:Int)
}

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
            val btnfavorito =itemView.findViewById<ImageButton>(R.id.add_deseo)
            btncarrito.setOnClickListener{
                action.OnItemClick(mercado,adapterPosition)
            }
            btnfavorito.setOnClickListener {
                action.OnDeseosClick(mercado, adapterPosition)
            }
        }

        /*
        var itemImage: ImageView
        var itemProducto: TextView
        var itemPrecio: TextView
        var itemDetalle: TextView

        init {
            itemImage = itemView.findViewById(R.id.imgMercado)
            itemProducto = itemView.findViewById(R.id.titleMercado)
            itemDetalle = itemView.findViewById(R.id.detallesMercado)
            itemPrecio = itemView.findViewById(R.id.precioMercado)
        }
         */
    }

    /*
    val productos= arrayOf("Harina Pan", "Café Aguila Roja", "Queso Colanta",
    "Aceite Frescampo", "Refresco Hit")
    val detalles = arrayOf("1000 gr", "500 gr", "400 gr", "3000 ml", "500 ml")
    val precios= arrayOf("$4.800", "$16.750", "$8.090", "$19.000", "$19.984", "$1.792")
    val images= arrayOf(R.drawable.p_harinapan, R.drawable.p_cafe, R.drawable.p_queso,
    R.drawable.p_aceite, R.drawable.p_hit)
    */

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var mercado=mercadolista[i]
        viewHolder.binWew(mercado, clickListener)
        /*
        viewHolder.itemProducto.text=productos[i]
        viewHolder.itemDetalle.text=detalles[i]
        viewHolder.itemPrecio.text=precios[i]
        viewHolder.itemImage.setImageResource(images[i])
        */
    }

    override fun getItemCount(): Int {
        return if (mercadolista.size>0){
            mercadolista.size
        }else{
            0
        }
    }

}
