package com.example.proyectokotiln

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.content.Context
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val emailKey = "EMAIL"
    private val passwordKey = "PASSWORD"
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

        auth = Firebase.auth

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val registerButton = findViewById<Button>(R.id.signup)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Registra al usuario utilizando Firebase Authentication
            registerUser(email, password)
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registro exitoso
                    val user = auth.currentUser
                    saveUserData(email, password)
                    showToast("Usuario registrado exitosamente")
                    finish()
                } else {
                    // Registro fallido
                    Log.e(TAG, "Error al registrar usuario: ${task.exception}")
                    showToast("Error al registrar usuario. Intente nuevamente.")
                }
            }
            .addOnFailureListener(this) { exception ->
                // Manejar el error
                Log.e(TAG, "Error al registrar usuario", exception)
                showToast("Error al registrar usuario. Intente nuevamente.")
            }
    }

    private fun saveUserData(email: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString(emailKey, email)
        editor.putString(passwordKey, password)
        editor.apply()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "RegisterActivity"
    }
}

