package com.oguzhancetin.kitgames

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_land)
        splashScreen.setKeepOnScreenCondition { true }
        object:CountDownTimer(2000,1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                splashScreen.setKeepOnScreenCondition{false}
            }

        }.start()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
}