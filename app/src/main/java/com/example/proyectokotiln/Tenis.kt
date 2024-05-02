package com.example.proyectokotiln

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class Tenis : AppCompatActivity() {

    companion object {
        private const val TAG = "Tenis"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_camisas)
        val db = FirebaseFirestore.getInstance()
        val productos = mutableListOf<Producto>()




        db.collection("Productos").whereEqualTo("categoria", "tenis").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val producto = document.toObject(Producto::class.java)
                    productos.add(producto)
                }
                Log.d(TAG, "Productos obtenidos: $productos")
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(this)
                val carrito = Carrito.getInstance() // Obtener la instancia existente de Carrito
                recyclerView.adapter = ProductoAdapter(productos, carrito) // Pasar la instancia de Carrito al constructor de ProductoAdapter
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "error en obtener los productos: ", exception)
            }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}