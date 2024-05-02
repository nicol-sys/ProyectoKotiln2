package com.example.proyectokotiln
import android.content.SharedPreferences
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FakeSharedPreferences : SharedPreferences {
    private val data = mutableMapOf<String, Any>()

    override fun getAll(): Map<String, *> {
        return data
    }

    override fun getString(key: String?, defValue: String?): String? {
        return data[key] as? String ?: defValue
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? {
        return if (data[key] is MutableSet<*>) {
            @Suppress("UNCHECKED_CAST")
            data[key] as MutableSet<String>
        } else {
            defValues
        }
    }

    override fun getInt(key: String?, defValue: Int): Int {
        return data[key] as? Int ?: defValue
    }

    override fun getLong(key: String?, defValue: Long): Long {
        return data[key] as? Long ?: defValue
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return data[key] as? Float ?: defValue
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return data[key] as? Boolean ?: defValue
    }

    override fun contains(key: String?): Boolean {
        return data.containsKey(key)
    }

    override fun edit(): SharedPreferences.Editor {
        return FakeEditor()
    }

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        // No necesitamos implementar esto para las pruebas
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        // No necesitamos implementar esto para las pruebas
    }

    inner class FakeEditor : SharedPreferences.Editor {
        override fun putString(key: String?, value: String?): SharedPreferences.Editor {
            data[key ?: ""] = value ?: ""
            return this
        }

        override fun putStringSet(key: String?, values: MutableSet<String>?): SharedPreferences.Editor {
            @Suppress("UNCHECKED_CAST")
            data[key ?: ""] = (values ?: mutableSetOf()) as MutableSet<String>
            return this
        }

        override fun putInt(key: String?, value: Int): SharedPreferences.Editor {
            data[key ?: ""] = value
            return this
        }

        override fun putLong(key: String?, value: Long): SharedPreferences.Editor {
            data[key ?: ""] = value
            return this
        }

        override fun putFloat(key: String?, value: Float): SharedPreferences.Editor {
            data[key ?: ""] = value
            return this
        }

        override fun putBoolean(key: String?, value: Boolean): SharedPreferences.Editor {
            data[key ?: ""] = value
            return this
        }

        override fun remove(key: String?): SharedPreferences.Editor {
            data.remove(key)
            return this
        }

        override fun clear(): SharedPreferences.Editor {
            data.clear()
            return this
        }

        override fun commit(): Boolean {
            // No necesitamos implementar esto para las pruebas
            return false
        }

        override fun apply() {
            // No necesitamos implementar esto para las pruebas
        }
    }
}

class UserDataTest {
    lateinit var sharedPreferences: FakeSharedPreferences
    private val emailKey = "email"
    private val passwordKey = "password"
    private lateinit var editor: SharedPreferences.Editor

    @Before
    fun setup() {
        sharedPreferences = FakeSharedPreferences()
        editor = sharedPreferences.edit()
    }

    @Test
    fun saveUserDataStoresEmailAndPasswordCorrectly() {
        val userData = UserData(sharedPreferences)

        val email1 = "example1@example.com"
        val password1 = "password123"

        userData.saveUserData(email1, password1)

        assertEquals(email1, sharedPreferences.getString(emailKey, null))
        assertEquals(password1, sharedPreferences.getString(passwordKey, null))

        val email2 = "example2@example.com"
        val password2 = "password456"

        userData.saveUserData(email2, password2)

        assertEquals(email2, sharedPreferences.getString(emailKey, null))
        assertEquals(password2, sharedPreferences.getString(passwordKey, null))
    }
}

class UserData(private val sharedPreferences: SharedPreferences) {
    private val emailKey = "email"
    private val passwordKey = "password"

    fun saveUserData(email: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString(emailKey, email)
        editor.putString(passwordKey, password)
        editor.apply()
    }
}