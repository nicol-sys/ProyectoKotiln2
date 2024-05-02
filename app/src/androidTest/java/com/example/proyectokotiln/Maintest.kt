package com.example.proyectokotiln

import androidx.test.core.app.ActivityScenario
import com.example.parcial.Authactivity
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class Maintest {
    @Test
    fun signInUser_withInvalidCredentials_failure() {
        val latch = CountDownLatch(1)
        var authActivity: Authactivity? = null
        ActivityScenario.launch(Authactivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                authActivity = activity
                authActivity?.signInUser("test@example.com", "passwordIncorrecta")
            }
        }
        latch.await(5, TimeUnit.SECONDS)
        assertFalse(authActivity?.isSuccessful ?: true)
    }

}