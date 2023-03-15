package com.rizkysiregar.skdrapp.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.rizkysiregar.skdrapp.MainActivity
import com.rizkysiregar.skdrapp.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // handler to make fake time load to show animation and app logo
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity((Intent(this, MainActivity::class.java)))
            finish()
        }, FAKE_LOAD_TIME)
    }

    companion object {
        private const val FAKE_LOAD_TIME: Long = 3000;
    }
}