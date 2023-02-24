package com.example.labo1

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.QuotesScreen.route){
        composable(route=Screen.QuotesScreen.route){
            QuotesScreen(navController=navController)
        }
        composable(
            route=Screen.ContentScreen.route+ "/{content}",
            arguments = listOf(
                navArgument("content"){
                    type = NavType.StringType
                    defaultValue = "Not found"
                }
            )
        ){entry->
            QuoteContentScreen(content=entry.arguments?.getString("content"))
        }
    }
}