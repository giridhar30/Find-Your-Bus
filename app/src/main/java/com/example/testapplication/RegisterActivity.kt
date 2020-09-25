package com.example.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    private val userCollectionRef = Firebase.firestore.collection("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        btnRegister.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()
            val user = User(name, phone, email)
            registerUser(user)
        }
    }

    private fun registerUser(user: User) {
        val name = user.name
        val phone = user.phone
        val email = user.email
        val password = etPassword.text.toString()
        val confirmPassword = etConfirmPassword.text.toString()
        val validationPassed = email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && password.equals(confirmPassword) && name.isNotEmpty() && phone.isNotEmpty() && phone.length==10
        if(validationPassed) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    userCollectionRef.add(user).await()
                    auth.createUserWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main) {
                        checkLoggedInState()
                    }
                    Log.d("RegInfo", "Register process done !")
                    if(auth.currentUser!=null) {
                        Intent(this@RegisterActivity, ChooseCityActivity::class.java).also {
                            startActivity(it)
                        }
                    } else {
                        Toast.makeText(this@RegisterActivity, "Error creating User !", Toast.LENGTH_LONG).show()
                        finish()
                    }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@RegisterActivity, e.message, Toast.LENGTH_LONG).show()
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