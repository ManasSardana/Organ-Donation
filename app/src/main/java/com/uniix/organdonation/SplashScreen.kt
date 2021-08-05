package com.uniix.organdonation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uniix.organdonation.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    //Initializing Variables
    private lateinit var splashActivity: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashActivity = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashActivity.root)

        Thread {
            Thread.sleep(1000)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }.start()
    }

}