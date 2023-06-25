package com.cibertec.burgerbross

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.cibertec.burgerbross.usuario.UsuarioManager

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val actionBar = supportActionBar
        actionBar?.hide()

        val emailUsuario = findViewById<TextView>(R.id.emailUsuarioPerfil)
        emailUsuario.text = UsuarioManager.obtenerEmail()
    }
}