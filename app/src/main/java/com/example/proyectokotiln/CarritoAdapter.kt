package com.example.proyectokotiln

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CarritoAdapter(private val productos: MutableList<productoCarrito>) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productoImagen: ImageView = itemView.findViewById(R.id.product_image)
        val productoNombre: TextView = itemView.findViewById(R.id.product_name)
        val productoCantidad: TextView = itemView.findViewById(R.id.product_quantity)
        val BotonEliminar: Button = itemView.findViewById(R.id.remove_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_carrito, parent, false)
        return CarritoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val productoCarrito = productos[position]

        Glide.with(holder.itemView)
            .load(productoCarrito.producto.imagen.firstOrNull())
            .into(holder.productoImagen)

        holder.productoNombre.text = productoCarrito.producto.nombre
        holder.productoCantidad.text = "Cantidad: ${productoCarrito.cantidad}"

        holder.BotonEliminar.setOnClickListener {
            productos.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount() = productos.size
}