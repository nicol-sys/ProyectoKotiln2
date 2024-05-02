package com.example.proyectokotiln

import androidx.recyclerview.widget.RecyclerView
import com.example.proyectokotiln.Producto
import com.example.proyectokotiln.productoCarrito

class Carrito private constructor() {
    val productos = mutableListOf<productoCarrito>()
    var recyclerView: RecyclerView? = null

    fun agregarProducto(producto: Producto) {
        val productoEnCarrito = productos.find { it.producto == producto }
        if (productoEnCarrito != null) {
            productoEnCarrito.cantidad++
        } else {
            productos.add(productoCarrito(producto))
        }
    }
    fun eliminarProducto(producto: Producto) {
        val productoEnCarrito = productos.find { it.producto == producto }
        productoEnCarrito?.let {
            it.cantidad--
            if (it.cantidad <= 0) {
                productos.remove(it)
            }
        }
    }

    companion object {
        @Volatile private var INSTANCE: Carrito? = null

        fun getInstance(): Carrito =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Carrito().also { INSTANCE = it }
            }
    }
}