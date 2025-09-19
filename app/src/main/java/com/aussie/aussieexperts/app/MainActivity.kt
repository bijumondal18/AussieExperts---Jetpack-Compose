package com.aussie.aussieexperts.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.aussie.aussieexperts.core.theme.AppTheme
import com.aussie.aussieexperts.presentation.pages.main.MainBottomNav

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var keepSplash = true
        splashScreen.setKeepOnScreenCondition { keepSplash }


        keepSplash = false

        setContent {
            AppTheme {
                MainBottomNav()
            }
        }


    }
}