package com.example.charmxhealth

import android.animation.ObjectAnimator
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.charmxhealth.presentation.navigation.Navgraph
import com.example.charmxhealth.ui.theme.CharmxHealthTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
           setKeepOnScreenCondition{
               viewModel.splashCondition
           }
        }
        if(!hasRequiredPermission()){
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSION, 0
            )
        }
        setContent {
            CharmxHealthTheme {
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                  systemController.setSystemBarsColor(
                      color = Color.Transparent,
                      darkIcons = !isSystemInDarkMode
                  )
                }

               Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)){
                   val startDestination = viewModel.startDestination
                   Navgraph(startDestination = startDestination)
               }
            }
        }
    }

    private fun hasRequiredPermission(): Boolean{
         return CAMERAX_PERMISSION.all{
             ContextCompat.checkSelfPermission(
                 applicationContext,it
             ) == PackageManager.PERMISSION_GRANTED
         }
    }

    companion object{
        private val CAMERAX_PERMISSION = arrayOf(
           android.Manifest.permission.CAMERA
        )
    }
}
