package com.example.ecommerceapp.ui

import AuthViewModel
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.ui.home.HomeScreen
import com.example.ecommerceapp.ui.notification.NotificationScreen
import com.example.ecommerceapp.ui.profile.ProfileScreen
import com.example.ecommerceapp.ui.search.SearchScreen


@Composable
fun MainScreen(
    rootNavController : NavController,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
){
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = bottomNavController,
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = Screen.Home.name,
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    bottom = paddingValues.calculateBottomPadding(),
                )

        ) {
            composable(Screen.Home.name) { HomeScreen() }
            composable(Screen.Search.name) { SearchScreen(navController = bottomNavController) }
            composable(Screen.Notification.name) { NotificationScreen(navController = bottomNavController) }
            composable(Screen.Profile.name) { ProfileScreen(
                navController = rootNavController ,
                authViewModel = authViewModel
            ) }
        }

    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
){
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Notification,
        Screen.Profile
    )

    NavigationBar(
        modifier = modifier
            .height(64.dp) // Slightly taller for better centering
            .navigationBarsPadding(), // Handles system nav bar if present
        tonalElevation = 6.dp
    )  {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen) {
                        Screen.Home -> Icon(Icons.Default.Home, contentDescription = "Home")
                        Screen.Search -> Icon(Icons.Default.Search, contentDescription = "Search")
                        Screen.Notification -> Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                        Screen.Profile -> Icon(Icons.Default.Person, contentDescription = "Profile")
                        else -> {}
                    }
                },
                selected = currentRoute == screen.name,
                onClick = {
                    navController.navigate(screen.name) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = false // Hides label and centers icon better
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    val navController = rememberNavController()
//    val authViewModel = AuthViewModel()
//    MainScreen(
//        navController = navController,
//        authViewModel = authViewModel
//    )
//}
