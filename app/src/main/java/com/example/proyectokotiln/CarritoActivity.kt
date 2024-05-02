package com.example.proyectokotiln

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CarritoActivity : AppCompatActivity() {

    private lateinit var carrito: Carrito
    private lateinit var textView: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)
        val textView = findViewById<TextView>(R.id.TituloCarrito)

        textView.text = "Carrito de compras"


        carrito = Carrito.getInstance() // Obtener la instancia existente de Carrito

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CarritoAdapter(carrito.productos)
    }

}