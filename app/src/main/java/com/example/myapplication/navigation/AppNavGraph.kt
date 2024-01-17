package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    homeScreenContent: @Composable () -> Unit,
    favouriteContent: @Composable () -> Unit,
    profileContent: @Composable () -> Unit,
) {
    NavHost(navController = navHostController,
        startDestination = Screen.NewsFeed.route,
        /*enterTransition = {
            scaleIn(
                animationSpec = tween(220, delayMillis = 90), initialScale = 1f
            ) + fadeIn(animationSpec = tween(220, delayMillis = 90))
        },
        exitTransition = {
            scaleOut(
                animationSpec = tween(durationMillis = 220, delayMillis = 90),
            ) + fadeOut(tween(delayMillis = 90))
        }*/) {
        composable(Screen.NewsFeed.route) {
            homeScreenContent()
        }
        composable(Screen.Favourite.route) {
            favouriteContent()
        }
        composable(Screen.Profile.route) {
            profileContent()
        }
    }
}

