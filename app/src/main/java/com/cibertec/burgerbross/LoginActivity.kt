package com.cibertec.burgerbross

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cibertec.burgerbross.usuario.LoginViewModel
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        val actionBar = supportActionBar
        actionBar?.hide()

        val edtEmail = findViewById<TextInputEditText>(R.id.loginEmail)
        val edtPass = findViewById<TextInputEditText>(R.id.loginPassword)

        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener{

            if(edtEmail.text != null && edtPass != null) {
                viewModel.login(edtEmail.text.toString(), edtPass.text.toString())
            } else {
                Toast.makeText(this, "Ingrese un email y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        observableViewModel()
    }

    fun observableViewModel() {
        viewModel.userLoginServiceResponse.observe(this){
            if (it){
                // login correcto
                startActivity(Intent(this, InicioActivity::class.java))
                finish()
            } else {
                // error
                Toast.makeText(this, "Email o contraseña incorrecta.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}