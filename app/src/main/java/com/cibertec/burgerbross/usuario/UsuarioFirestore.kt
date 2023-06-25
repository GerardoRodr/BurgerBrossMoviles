package com.cibertec.burgerbross.usuario

data class UsuarioFirestore(
    var correo: String,
    var contraseña: String
) {
    var nombre: String = ""
}