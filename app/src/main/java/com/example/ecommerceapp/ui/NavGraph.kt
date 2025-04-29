package com.example.ecommerceapp.ui

import AuthViewModel
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.ui.Onboarding.OnBoardingLayout
import com.example.ecommerceapp.ui.Onboarding.OnBoardingViewModel
import com.example.ecommerceapp.ui.auth.AuthViewModelFactory
import com.example.ecommerceapp.ui.auth.ForgetPasswordScreen
import com.example.ecommerceapp.ui.auth.LoginScreen
import com.example.ecommerceapp.ui.auth.RegisterScreen

@Composable
fun NavGraph(context: Context) {
    val navController = rememberNavController()
    val onBoardingViewModel: OnBoardingViewModel = viewModel()

    // SharedPreferences to manage app settings
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val authViewModelFactory = remember { AuthViewModelFactory(sharedPreferences) }
    val authViewModel: AuthViewModel = viewModel(factory = authViewModelFactory)


    // Call checkLoginState to verify if the user is logged in on app launch
    authViewModel.checkLoginState()

    // Observe the login state
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    // Check login state and navigate accordingly
    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate("main") {
                popUpTo(Screen.Login.name) { inclusive = true }
            }
        }
    }

    // Check if this is the first launch to decide the start destination
    val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

    // If you want to reset the first launch flag, call this method
    //resetFirstLaunchFlag(sharedPreferences)


    // Setup NavHost with navigation flow based on the app state
    NavHost(
        navController = navController,
        startDestination = when {
            isLoggedIn -> "main"  // Go to main if logged in
            isFirstLaunch -> Screen.Onboarding1.name // Show onboarding if it's the first launch
            else -> Screen.Login.name  // Show login screen otherwise
        }
    ) {
        // Onboarding screens
        composable(route = Screen.Onboarding1.name) {
            OnBoardingLayout(
                image = R.drawable.choose_product,
                title = R.string.choose_product_title,
                description = R.string.choose_product_des,
                viewModel = onBoardingViewModel,
                navController = navController
            )
        }

        composable(route = Screen.Onboarding2.name) {
            OnBoardingLayout(
                image = R.drawable.make_payment,
                title = R.string.make_payment_title,
                description = R.string.make_payment_des,
                viewModel = onBoardingViewModel,
                navController = navController
            )
        }

        composable(route = Screen.Onboarding3.name) {
            OnBoardingLayout(
                image = R.drawable.get_your_order,
                title = R.string.get_order_title,
                description = R.string.get_order_des,
                viewModel = onBoardingViewModel,
                navController = navController
            )
        }

        // Login screen
        composable(route = Screen.Login.name) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignup = {
                    authViewModel.resetFields()
                    navController.navigate(Screen.Register.name)
                },
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo(Screen.Login.name) { inclusive = true }
                    }
                    sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
                },
                onNavigateToForget = {
                    authViewModel.resetFields()
                    navController.navigate(Screen.Forget_Password.name)
                }
            )
        }

        // Register screen
        composable(route = Screen.Register.name) {
            RegisterScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = {
                    authViewModel.resetFields()
                    navController.navigate(Screen.Login.name)
                },
                onRegisterSuccess = {
                    authViewModel.resetFields()
                    navController.navigate(Screen.Login.name)
                }
            )
        }

        // Forget Password screen
        composable(route = Screen.Forget_Password.name) {
            ForgetPasswordScreen(
                authViewModel = authViewModel,
                onResetLinkSent = {
                    authViewModel.resetFields()
                    navController.navigate(Screen.Login.name)
                },
                onNavigateToLogin = {
                    authViewModel.resetFields()
                    navController.navigate(Screen.Login.name)
                }
            )
        }

        // Main screen after login
        composable(route = "main") {
            MainScreen(rootNavController = navController, authViewModel = authViewModel)
        }
    }
}

fun resetFirstLaunchFlag(sharedPreferences: SharedPreferences) {
    sharedPreferences.edit().putBoolean("isFirstLaunch", true).apply()
}
