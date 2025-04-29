package com.example.ecommerceapp.ui.profile

import AuthViewModel
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.ecommerceapp.ui.Screen

@Composable
fun ProfileScreen(navController: NavController, authViewModel: AuthViewModel) {
    val context = LocalContext.current

    Column {
        Text("Welcome to the Profile Screen")

        Button(onClick = {
            authViewModel.logout(
                onSuccess = {
                    navController.navigate(Screen.Login.name) {
                        popUpTo("main") { inclusive = true }
                    }
                },
                onError = { error ->
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            )
        }) {
            Text("Logout")
        }
    }
}