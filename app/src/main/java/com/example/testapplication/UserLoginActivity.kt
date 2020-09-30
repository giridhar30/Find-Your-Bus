package com.example.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_user_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserLoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)
        auth = FirebaseAuth.getInstance()

        btnLoginUser.setOnClickListener {
            loginUser()
            if(auth.currentUser!=null) {
                Intent(this, ChooseCityActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

    private fun loginUser() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        if(email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        checkLoggedInState()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@UserLoginActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "Invalid Credentials !", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkLoggedInState() {
        if(auth.currentUser==null) {
            Toast.makeText(this, "You are not Logged in !", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "You are Logged in !", Toast.LENGTH_LONG).show()
        }
    }

}