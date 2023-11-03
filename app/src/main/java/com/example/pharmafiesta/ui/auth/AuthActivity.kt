package com.example.pharmafiesta.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pharmafiesta.ui.auth.signin.SignInScreenUi
import com.example.pharmafiesta.ui.auth.signup.SignupScreenUi
import com.example.pharmafiesta.ui.home.BottomNavigationActivity
import com.example.pharmafiesta.ui.splash.SplashScreenUi
import com.example.pharmafiesta.ui.theme.PharmaFiestaTheme


private const val TAG = "AuthActivity"


class AuthActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PharmaFiestaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupAppRouteNavigation(){
                        startActivity(Intent(this,BottomNavigationActivity::class.java))
                        this.finish()
                    }
                }
            }
        }
    }
}

@Composable
private fun SetupAppRouteNavigation(onSignInButtonClicked: () -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AuthScreensRoutes.SplashScreenRoute.route
    ) {
        composable(route = AuthScreensRoutes.SplashScreenRoute.route) {
            Log.d(TAG, "SetupAppRouteNavigation: SplashScreen")
            SplashScreenUi(navController = navController)
        }
        composable(route = AuthScreensRoutes.SplashScreenRoute.route + "/" + AuthScreensRoutes.SignupScreenRoute.route) {
            Log.d(TAG, "SetupAppRouteNavigation: SignupScreen")
            SignupScreenUi(navController = navController)
        }
        composable(route = AuthScreensRoutes.SplashScreenRoute.route + "/${AuthScreensRoutes.SignupScreenRoute.route}" + "/${AuthScreensRoutes.SignInScreenRoute.route}") {
            Log.d(TAG, "SetupAppRouteNavigation: SignInScreenUi")
            SignInScreenUi(onSignInButtonClicked)
        }
    }
}