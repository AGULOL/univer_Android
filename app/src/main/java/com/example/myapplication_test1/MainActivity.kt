package com.example.myapplication_test1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var editLogin: EditText
    private lateinit var editPassword: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first) //

        editLogin = findViewById(R.id.editTextLogin)
        editPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.next_screen_button)

        buttonLogin.setOnClickListener {
            val login = editLogin.text.toString().trim()
            val password = editPassword.text.toString()

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //
            val correctLogin = "user"
            val correctPassword = "password"

            if (login == correctLogin && password == correctPassword) {
                Toast.makeText(this, "Успешный вход", Toast.LENGTH_SHORT).show()

                //
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
