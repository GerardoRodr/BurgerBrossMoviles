package com.cibertec.burgerbross.usuario

data class UsuarioFirestore(
    val correo: String,
    val contraseña: String
) {
    val nombre: String = ""
}