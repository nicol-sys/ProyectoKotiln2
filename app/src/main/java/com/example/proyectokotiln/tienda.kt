package com.example.proyectokotiln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.SearchView
import android.widget.LinearLayout
import android.view.View
import android.content.Intent
import android.widget.Button
import android.widget.ImageView


class tienda : AppCompatActivity() {

    public var isSearchViewClosed = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tienda)

        findViewById<Button>(R.id.buttonOption1).setOnClickListener {
            val intent = Intent(this, Camisas::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.buttonOption2).setOnClickListener {
            val intent = Intent(this, Chaquetas::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.buttonOption3).setOnClickListener {
            val intent = Intent(this, Pantalones::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.buttonOption4).setOnClickListener {
            val intent = Intent(this, Tenis::class.java)
            startActivity(intent)
        }
        val botonCarrito = findViewById<ImageView>(R.id.boton_carrito)
        botonCarrito.setOnClickListener {
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }

        val email = intent.getStringExtra("EMAIL")
        findViewById<TextView>(R.id.nombreusaer).text = "Bienvenido, $email"

        val searchView = findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (newText.isEmpty() && isSearchViewClosed) mostrarTodosLinearLayouts()
                    else manejarTextoBusqueda(newText)
                }
                return true
            }
        })

        searchView.setOnCloseListener {
            isSearchViewClosed = true
            mostrarTodosLinearLayouts()
            false
        }

        searchView.setOnSearchClickListener { isSearchViewClosed = false }
    }

    public fun mostrarTodosLinearLayouts() {
        val layouts = listOf(
            R.id.layout_camisas,
            R.id.layout_chaquetas,
            R.id.layout_pantalones,
            R.id.layout_tenis
        )
        layouts.forEach { findViewById<LinearLayout>(it).visibility = View.VISIBLE }
    }

    public fun manejarTextoBusqueda(newText: String) {
        val layouts = listOf(
            R.id.layout_camisas,
            R.id.layout_chaquetas,
            R.id.layout_pantalones,
            R.id.layout_tenis
        )

        layouts.forEach { findViewById<LinearLayout>(it).visibility = View.GONE }

        when (newText.toLowerCase()) {
            "camisas","ca","cam", "cami", "camis", "camisa" -> findViewById<LinearLayout>(R.id.layout_camisas).visibility = View.VISIBLE
            "chaquetas","ch","cha","chaq", "chaqu","chaque","chaquet","chaqueta" -> findViewById<LinearLayout>(R.id.layout_chaquetas).visibility = View.VISIBLE
            "pantalones","p","pa","pan", "pant","panta", "pantal", "pantalo","pantalon","pantalone" -> findViewById<LinearLayout>(R.id.layout_pantalones).visibility = View.VISIBLE
            "tenis","t","te","ten","teni" -> findViewById<LinearLayout>(R.id.layout_tenis).visibility = View.VISIBLE
        }
    }

}

