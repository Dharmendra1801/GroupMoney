package com.example.groupmoney

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun App(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRouteClass.DataInputScreen.route) {
        composable(route = NavigationRouteClass.DataInputScreen.route) {
            val finalDetailsBack = navController.previousBackStackEntry?.savedStateHandle?.get<FinalDetails>("finalDetailBack") ?: FinalDetails(emptyList(), emptyList())
            dataEntryScreen( finalDetails = finalDetailsBack,
                goToCalculationPage = {
                navController.currentBackStackEntry?.savedStateHandle?.set("finalDetail",it)
                navController.navigate(NavigationRouteClass.ResultScreen.route)
            })
        }
        composable(route = NavigationRouteClass.ResultScreen.route) {
            val result = navController.previousBackStackEntry?.savedStateHandle?.get<FinalDetails>("finalDetail") ?: FinalDetails(emptyList(), emptyList())
            ResultScreen(goToDataScreen = {
                navController.currentBackStackEntry?.savedStateHandle?.set("finalDetailBack",it)
                navController.navigate(NavigationRouteClass.DataInputScreen.route)},
                finalDetails = result)
        }
    }
}

