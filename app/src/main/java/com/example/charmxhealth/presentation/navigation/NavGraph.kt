package com.example.charmxhealth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.charmxhealth.MainViewModel
import com.example.charmxhealth.presentation.AssistantFlow.Assistant.AppContent
import com.example.charmxhealth.presentation.AssistantFlow.Assistant.Camera.CameraScreen
import com.example.charmxhealth.presentation.onBoarding.Login.LoginAsDoctor.loginAsDoctor
import com.example.charmxhealth.presentation.onBoarding.Login.LoginAsPatient.loginAsPatientScreen
import com.example.charmxhealth.presentation.onBoarding.Login.LoginScreen
import com.example.charmxhealth.presentation.onBoarding.OnBoardingViewModel
import com.example.charmxhealth.presentation.onBoarding.onBoardingScreen

@Composable
fun Navgraph(
    startDestination : String
){
    val NavController = rememberNavController()
    val viewModel2 : MainViewModel = hiltViewModel()

    NavHost(navController = NavController, startDestination = startDestination){
         navigation(
             route = Route.AppStartNavigation.route,
             startDestination = Route.OnBoardingScreen.route
         ){
             composable(
                 route = Route.OnBoardingScreen.route
             ){
                 val viewModel : OnBoardingViewModel = hiltViewModel()
                 onBoardingScreen(event =  viewModel::OnEvent)
             }

         }

        navigation(
            route = Route.HomeNavigation.route,
            startDestination = Route.AssistantScreen.route
        ){

            composable(
                route = Route.AssistantScreen.route
            ){entry ->

                AppContent(navController = NavController, viewModel2 = viewModel2)
            }

            composable(
                route = Route.CameraScreen.route
            ){

                CameraScreen(navController = NavController, viewModel2 = viewModel2)
            }
        }
    }
}