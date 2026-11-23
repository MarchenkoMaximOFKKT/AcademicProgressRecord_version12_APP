package com.example.AcademicProgressRecord_version12

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            SubjectListScreen(
                onSubjectClick = { id -> navController.navigate("details/$id") }
            )
        }
        composable(
            route = "details/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) {
            DetailScreen(onBack = { navController.popBackStack() })
        }
    }
}