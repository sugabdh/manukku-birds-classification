package com.bangkit.manukku.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bangkit.manukku.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        val splashTime = 3000
        Handler().postDelayed({
            val intentToMainActivity = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intentToMainActivity)
            finish()
        }, splashTime.toLong())
    }
}