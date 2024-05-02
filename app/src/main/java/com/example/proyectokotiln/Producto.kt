package com.example.proyectokotiln

data class Producto(
    val nombre: String = "",
    val categoria: String = "",
    val precio: Double = 0.0,
    val imagen: List<String> = listOf()
)
