package com.cibertec.burgerbross.usuario

object UsuarioManager {
    private val usuario: UsuarioFirestore = UsuarioFirestore("", "")

    fun obtenerUsuario(): UsuarioFirestore {
        return usuario
    }

    fun obtenerEmail() : String {
        return usuario.correo
    }

    fun asignarUsuario(correo: String, contraseña: String) {
        usuario.correo = correo
        usuario.contraseña = contraseña
    }
}