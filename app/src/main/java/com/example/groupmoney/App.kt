package com.example.groupmoney

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun App(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRouteClass.DataInputScreen.route) {
        composable(route = NavigationRouteClass.DataInputScreen.route) {
            dataEntryScreen(goToCalculationPage = {
                navController.currentBackStackEntry?.savedStateHandle?.set("finalDetail",it)
                navController.navigate(NavigationRouteClass.ResultScreen.route)
            })
        }
        composable(route = NavigationRouteClass.ResultScreen.route) {
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<FinalDetails>("finalDetail") ?: FinalDetails(emptyList(), emptyList())
            ResultScreen(finalDetails = result)
        }
    }
}