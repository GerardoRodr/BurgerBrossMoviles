package com.cibertec.burgerbross

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar?.hide()

        val btnMain = findViewById<Button>(R.id.btn_main)
        btnMain.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}