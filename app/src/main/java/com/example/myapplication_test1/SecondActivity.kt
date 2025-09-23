package com.example.myapplication_test1

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button

import androidx.activity.ComponentActivity

class SecondActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_second)
        findViewById<Button>(R.id.button_back).setOnClickListener {
//            val context: Context = this@MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}