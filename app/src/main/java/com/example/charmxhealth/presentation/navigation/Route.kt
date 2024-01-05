package com.example.charmxhealth.presentation.navigation

sealed class Route(
    val route : String
) {

    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object HomeNavigation : Route(route = "homeNavigation")
    object LoginNavigationScreen : Route(route = "loginScreen")
    object LoginAsPatientScreen : Route(route = "loginAsPatient")
    object LoginAsDoctorScreen : Route(route = "loginAsDoctor")
    object AssistantScreen : Route(route = "assistant_screen")
    object UploadRecordScreen : Route(route = "uploadRecordScreen")
    object UPloadRecordPictures : Route(route = "uploadRecordPictures")
    object CameraScreen : Route(route = "camera_screen")
}