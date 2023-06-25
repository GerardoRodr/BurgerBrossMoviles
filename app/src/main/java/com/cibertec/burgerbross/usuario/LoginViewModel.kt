package com.cibertec.burgerbross.usuario

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
    private lateinit var auth: FirebaseAuth
    val userLoginServiceResponse = MutableLiveData<Boolean>()

    fun login(email: String, pass: String) {
        loginFirebase(email, pass)
    }

    private fun loginFirebase(email: String, pass: String) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener(Activity()) { task ->
                if(task.isSuccessful) {
                    val userId = task.result?.user?.uid
                    userLoginServiceResponse.value = true
                } else {
                    userLoginServiceResponse.value = false
                }
            }
    }

}