package com.rvcode.atomberg_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rvcode.atomberg_app.views.FanControllerScreen
import com.rvcode.atomberg_app.views.LoginScreen
import com.rvcode.atomberg_app.views.ProductScreen


@Composable
fun NavigationGraph(modifier: Modifier,navHostController: NavHostController) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = "login"
    ){

        composable("login") {
            LoginScreen(navHostController=navHostController)
        }

        composable(route = "product") {
            ProductScreen(navHostController)
        }

        composable(route = "controller/{deviceId}") {navBackStack->
            val deviceId = navBackStack.arguments?.getString("deviceId")
            FanControllerScreen(navHostController = navHostController, deviceId = deviceId)

        }
    }
}