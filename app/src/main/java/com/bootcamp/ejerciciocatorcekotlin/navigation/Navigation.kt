package com.bootcamp.ejerciciocatorcekotlin.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.bootcamp.ejerciciocatorcekotlin.viewmodel.PatientViewModel
import com.bootcamp.ejerciciocatorcekotlin.view.PatientsView
import com.bootcamp.ejerciciocatorcekotlin.view.HomeView


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "patients") {
        composable("patients") {
            val patientViewModel: PatientViewModel = viewModel()
            PatientsView(navController = navController, patientviewModel = patientViewModel)
        }
        composable("home/{patientId}") { backStackEntry ->
            val patientId = backStackEntry.arguments?.getString("patientId")
            val patientViewModel: PatientViewModel = viewModel()
            HomeView(navController, patientId = patientId,  patientviewModel = patientViewModel)
        }
    }
}


//fun Navigation(navController: NavHostController) {
//val patientsViewModel: PatientViewModel = viewModel()
//val navController = rememberNavController()
//NavHost(navController = navController, startDestination = "home" ){
  //  composable(route = "home") { PatientsView(navController,viewModel = patientsViewModel)}
    //composable(route = "imc") { HomeView(navController)}

