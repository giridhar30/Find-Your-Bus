package com.example.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_choose_city.*

class ChooseCityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_city)

        btnSelectCity.setOnClickListener {
            Toast.makeText(this, "You chose your city !", Toast.LENGTH_LONG).show()
        }
    }
}