package com.example.proyectokotiln

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import android.widget.LinearLayout
import android.view.View
import androidx.test.core.app.ApplicationProvider
import android.widget.TextView
import android.content.Intent
import org.junit.Assert.assertNotNull


@RunWith(AndroidJUnit4::class)
class test {

    @Test
    fun onCreate_setsUsernameInTextView() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), tienda::class.java).apply {
            putExtra("EMAIL", "UsuarioEjemplo")
        }

        var activity: tienda? = null
        var nombreUsuarioTextView: TextView? = null

        // Use ActivityScenario to launch and interact with the activity
        ActivityScenario.launch<tienda>(intent).onActivity { currentActivity ->
            activity = currentActivity
            nombreUsuarioTextView = currentActivity.findViewById(R.id.nombreusaer)
        }

        // Check if activity and TextView are not null
        assertNotNull(activity)
        assertNotNull(nombreUsuarioTextView)

        // Perform your assertions
        assertEquals("Bienvenido, UsuarioEjemplo", nombreUsuarioTextView!!.text)
    }

    @Test
    fun testManejarTextoBusqueda() {
        ActivityScenario.launch(tienda::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val tienda = activity as tienda
                tienda.manejarTextoBusqueda("camisas")
                val camisasLayout = activity.findViewById<LinearLayout>(R.id.layout_camisas)
                assertEquals("El layout de camisas debería estar visible", View.VISIBLE, camisasLayout.visibility)

                tienda.manejarTextoBusqueda("chaquetas")
                val chaquetasLayout = activity.findViewById<LinearLayout>(R.id.layout_chaquetas)
                assertEquals("El layout de chaquetas debería estar visible", View.VISIBLE, chaquetasLayout.visibility)

                tienda.manejarTextoBusqueda("pantalones")
                val pantalonesLayout = activity.findViewById<LinearLayout>(R.id.layout_pantalones)
                assertEquals("El layout de pantalones debería estar visible", View.VISIBLE, pantalonesLayout.visibility)

                tienda.manejarTextoBusqueda("tenis")
                val tenisLayout = activity.findViewById<LinearLayout>(R.id.layout_tenis)
                assertEquals("El layout de tenis debería estar visible", View.VISIBLE, tenisLayout.visibility)

                tienda.manejarTextoBusqueda("zapatos") // Prueba con un texto no reconocido
                assertEquals("Ningún layout debería estar visible", View.GONE, camisasLayout.visibility)
                assertEquals("Ningún layout debería estar visible", View.GONE, chaquetasLayout.visibility)
                assertEquals("Ningún layout debería estar visible", View.GONE, pantalonesLayout.visibility)
                assertEquals("Ningún layout debería estar visible", View.GONE, tenisLayout.visibility)
            }
        }
    }
    @Test
    fun testMostrarTodosLinearLayouts() {

        ActivityScenario.launch(tienda::class.java).use { scenario ->
            scenario.onActivity { activity ->
                val tienda = activity as tienda
                tienda.mostrarTodosLinearLayouts()
                val layouts = listOf(
                    R.id.layout_camisas,
                    R.id.layout_chaquetas,
                    R.id.layout_pantalones,
                    R.id.layout_tenis
                )
                layouts.forEach { id ->
                    val layout = activity.findViewById<LinearLayout>(id)
                    assertEquals(
                        "El LinearLayout con ID $id no está visible",
                        LinearLayout.VISIBLE,
                        layout.visibility
                    )
                }
            }
        }
    }
}



