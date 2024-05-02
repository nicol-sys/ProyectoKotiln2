package com.example.proyectokotiln

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ProductoAdapter(private val productos: List<Producto>, private val carrito: Carrito) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nombreProducto: TextView = view.findViewById(R.id.producto_nombre)
        val imagenProducto: ImageView = view.findViewById(R.id.producto_imagen)
        val precioProducto: TextView = view.findViewById(R.id.producto_precio)
        val agregarAlCarrito: ImageView = view.findViewById(R.id.boton_agregar)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.producto_item, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]

        holder.nombreProducto.text = producto.nombre
        holder.precioProducto.text = producto.precio.toString()

        holder.agregarAlCarrito.setOnClickListener {
            carrito.agregarProducto(producto)
            (carrito.recyclerView?.adapter as? CarritoAdapter?)?.notifyDataSetChanged()
            Toast.makeText(it.context, "Producto añadido al carrito", Toast.LENGTH_SHORT).show()
        }

        if (producto.imagen.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(producto.imagen[0])
                .into(holder.imagenProducto)
        }
    }

    override fun getItemCount() = productos.size
}