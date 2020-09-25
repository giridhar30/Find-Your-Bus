package com.example.testapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
/*
import com.geospark.lib.GeoSpark
import com.geospark.lib.callback.GeoSparkCallBack
import com.geospark.lib.model.GeoSparkError
import com.geospark.lib.model.GeoSparkUser
*/
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        GeoSpark.initialize(this.application, getString(R.string.geospark_api_key))

//        GeoSpark.createUser(this, "User Description", object : GeoSparkCallBack {
//            override fun onSuccess(geoSparkUser: GeoSparkUser) {
//                geoSparkUser.userId
//            }
//
//            override fun onFailure(geoSparkError: GeoSparkError) {
//                geoSparkError.errorCode
//                geoSparkError.errorMessage
//            }
//        })
//
//        if(!GeoSpark.checkLocationPermission(this)) {
//            GeoSpark.requestLocationPermission(this)
//        } else if (!GeoSpark.checkLocationServices(this)) {
//            GeoSpark.requestLocationServices(this)
//        } else{
//            //Call this method to start tracking the location.
//            GeoSpark.startTracking(this)
//        }

        btnRegistration.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }

        btnUserLogin.setOnClickListener {
            Intent(this, UserLoginActivity::class.java).also {
                startActivity(it)
            }
        }

        btnBusLogin.setOnClickListener {
            Intent(this, BusLoginActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}