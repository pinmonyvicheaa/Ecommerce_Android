package com.example.ecommerceapp.ui.Onboarding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.ecommerceapp.ui.Screen

class OnBoardingViewModel : ViewModel() {
    private val _currentPage = MutableLiveData(1)
    val currentPage: LiveData<Int> get() = _currentPage

    fun nextPage(navController: NavHostController) {
        val current = _currentPage.value ?: 1
        Log.d("OnBoardingViewModel", "Next button clicked. Current page: $current")

        if (current < 3) {
            _currentPage.value = current + 1
            navController.navigate("Onboarding${current + 1}") {
                popUpTo(Screen.Onboarding1.name) { inclusive = false }  // Ensures user can go back to the first page if needed
            }
        } else {
            navController.navigate(Screen.Login.name) {
                popUpTo(Screen.Onboarding1.name) { inclusive = true }  // Clears all onboarding screens
            }
        }

        Log.d("OnBoardingViewModel", "Navigated to page: ${_currentPage.value}")
    }

    fun previousPage(navController: NavController) {
        val current = _currentPage.value ?: 1
        Log.d("OnBoardingViewModel", "Prev button clicked. Current page: $current")

        if (current > 1) {
            _currentPage.value = current - 1
            navController.navigate("Onboarding${current - 1}") {
                popUpTo(Screen.Onboarding1.name) { inclusive = false }
            }
        }

        Log.d("OnBoardingViewModel", "Navigated to page: ${_currentPage.value}")
    }

    fun skip(navController: NavController) {
        Log.d("OnBoardingViewModel", "Skip button clicked. Navigating to Login screen")
        navController.navigate(Screen.Login.name) {
            popUpTo(Screen.Onboarding1.name) { inclusive = true }  // Clears onboarding flow from backstack
        }
    }
}
