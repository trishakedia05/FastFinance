package com.example.finance.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finance.screens.main.MainScreen
import com.example.finance.screens.main.MainViewModel
import com.example.finance.screens.main.MyMainScreen

import com.example.finance.screens.settings.SettingsScreen
import com.example.finance.screens.splash.My_SplashScreen

@Composable
fun app_navigation() {
    val navController= rememberNavController()
    NavHost(navController = navController,
        startDestination = allScreens.SplashScreen.name){
        composable(allScreens.SplashScreen.name){
            My_SplashScreen(navController=navController)
        }
        composable(allScreens.MainScreen.name){
            val mainViewModel= hiltViewModel<MainViewModel>()
            MyMainScreen(navController =navController,mainViewModel)
        }
        composable(allScreens.SettingScreen.name){
            val mainViewModel= hiltViewModel<MainViewModel>()
            SettingsScreen(navController=navController,mainViewModel)
        }

    }
}